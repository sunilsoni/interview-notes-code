package com.interview.notes.code.year.y2026.feb.microsoft.test12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VmService { // Main class encapsulating the API logic and tests.

    // Global thread-safe map holding the live tracking state of every active subscription.
    static Map<String, SubState> states = new ConcurrentHashMap<>(); // Concurrent map ensures thread safety at the global level.
    // Global map simulating a database lookup for customer quota rules.
    static Map<String, Quota> quotas = Map.of("SubA", new Quota(4, 16), "SubBig", new Quota(100000, 400000)); // Sample data.

    // Core processing method invoked by your API endpoint controllers.
    static boolean processRequest(ApiVM req) { // Handles a single VM creation request workflow.
        var q = quotas.get(req.subId()); // Fetch the allowed quota limits for this specific customer.
        if (q == null) return false; // Fail immediately if the subscription ID does not exist.

        // Get the state tracker for this subscription, creating it atomically if it's their first request.
        var state = states.computeIfAbsent(req.subId(), k -> new SubState()); // Guarantees a state object exists.

        // Phase 1: Fast reservation of quota (Takes < 1 millisecond).
        if (!state.reserve(req, q)) return false; // Exit immediately if quota is full or name exists.

        // Phase 2: Slow operations execute OUTSIDE the synchronized lock so we don't block other customers.
        boolean valid = validateInput(req); // Call the slow validation method.
        if (!valid) { // Check if the validation failed.
            state.rollback(req); // Revert the reserved quota since we won't build the VM.
            return false; // Return failure to the customer.
        }

        // Phase 3: Slow database persistence operation.
        boolean persisted = persistVM(req); // Call the slow database persistence method.
        if (!persisted) { // Check if the database save failed.
            state.rollback(req); // Revert the reserved quota since it didn't save.
            return false; // Return failure to the customer.
        }

        return true; // Successfully created the VM within limits.
    }

    // Simulated slow method exactly as described in the problem image.
    static boolean validateInput(ApiVM input) { // Stub representing your complex validation.
        try { Thread.sleep(100); } catch (Exception e) {} // Halts thread to simulate taking hundreds of ms.
        return true; // We assume validation passes for the sake of these tests.
    }

    // Simulated slow method exactly as described in the problem image.
    static boolean persistVM(ApiVM input) { // Stub representing your database persistence.
        try { Thread.sleep(100); } catch (Exception e) {} // Halts thread to simulate taking hundreds of ms.
        return true; // We assume persistence passes for the sake of these tests.
    }

    // Simple main method for testing, explicitly avoiding JUnit as requested.
    public static void main(String[] args) throws Exception { // Application entry point.
        System.out.println("Starting execution tests..."); // Console output to track progress.

        // TEST 1: Small Trial Customer (Strict Limits under high concurrency)
        // Java 21 feature: Virtual Threads are incredibly lightweight and perfect for high concurrency.
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { // Opens an auto-closing virtual thread pool.
            var futures = new ArrayList<Future<Boolean>>(); // List to track the results of our async requests.

            // Fire 10 concurrent requests at exactly the same time for a customer limited to 4 VMs.
            for (int i = 0; i < 10; i++) { // Loop to generate requests.
                var req = new ApiVM("SubA", "VM-" + i, 2, "Windows"); // Create an input payload.
                futures.add(executor.submit(() -> processRequest(req))); // Dispatch the payload to a virtual thread.
            }

            int successCount = 0; // Counter to tally successful VM creations.
            for (var f : futures) if (f.get()) successCount++; // Wait for thread to finish and count if it passed.

            // Assert expectation: Only 4 must succeed, blocking the other 6.
            if (successCount == 4) System.out.println("Test 1 PASS: Free tier restricted to exactly 4 VMs."); // Print pass.
            else System.out.println("Test 1 FAIL: Expected 4, got " + successCount); // Print fail.
        }

        // TEST 2: Enterprise Customer (Handling large data inputs cases concurrently)
        states.clear(); // Reset global state for a clean test environment.
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { // Re-open virtual thread pool.
            var futures = new ArrayList<Future<Boolean>>(); // Tracker list for the enterprise workload.

            // Fire 5,000 concurrent requests at exactly the same time.
            for (int i = 0; i < 5000; i++) { // Massive loop simulating an API flood.
                var req = new ApiVM("SubBig", "AppServer-" + i, 4, "Linux"); // Large customer request payload.
                futures.add(executor.submit(() -> processRequest(req))); // Submit to pool immediately.
            }

            int successCount = 0; // Success counter for enterprise batch.
            for (var f : futures) if (f.get()) successCount++; // Aggregate the results.

            // Assert expectation: Since their limit is 100,000, all 5,000 should pass smoothly.
            if (successCount == 5000) System.out.println("Test 2 PASS: Handled 5000 concurrent enterprise requests smoothly."); // Print pass.
            else System.out.println("Test 2 FAIL: Expected 5000, got " + successCount); // Print fail.
        }
    }

    // Using Java 21 'record' to create immutable data objects with zero boilerplate code.
    record ApiVM(String subId, String vmName, int cores, String os) {} // Represents the incoming VM payload.

    record Quota(int maxVms, int maxCores) {} // Represents the maximum limits assigned to a customer.

    // State tracker for a single subscription to manage its used resources safely in memory.
    static class SubState { // Encapsulates the mutable state for one customer.
        int usedVms = 0; // Tracks the currently allocated VM count.
        int usedCores = 0; // Tracks the currently allocated CPU cores.
        Set<String> vms = new HashSet<>(); // Tracks VM names to prevent duplicate creations.

        // 'synchronized' locks this specific subscription state so concurrent threads check rules one-at-a-time.
        synchronized boolean reserve(ApiVM vm, Quota q) { // Attempts to lock resources before slow operations.
            if (vms.contains(vm.vmName())) return false; // Fast-fail reject if the VM name is already in use.
            if (usedVms + 1 > q.maxVms()) return false; // Fast-fail reject if adding 1 VM exceeds their limit.
            if (usedCores + vm.cores() > q.maxCores()) return false; // Fast-fail reject if adding cores exceeds limits.
            usedVms++; // Increase allocated VM count in our tracker.
            usedCores += vm.cores(); // Add the new cores to the total allocated count.
            vms.add(vm.vmName()); // Mark this specific VM name as taken so duplicates fail.
            return true; // Return success, meaning the quota is reserved and safe to proceed.
        }

        // 'synchronized' safely reverts the state if the subsequent slow operations fail.
        synchronized void rollback(ApiVM vm) { // Frees up resources if validation or persistence fails.
            usedVms--; // Reduce the VM count back to previous state.
            usedCores -= vm.cores(); // Reduce the core count back to previous state.
            vms.remove(vm.vmName()); // Free up the VM name so they can try again.
        }
    }
}