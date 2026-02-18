package com.interview.notes.code.year.y2026.feb.microsoft.test11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VmService { // Main class containing our logic and tests.

    // 3. Global State
    static Map<String, SubState> states = new ConcurrentHashMap<>(); // Thread-safe map holding state for all active customers.
    static Map<String, Quota> quotas = Map.of("SubA", new Quota(4, 16), "SubBig", new Quota(100000, 400000)); // Static database mock.

    // 4. Main Processing Workflow
    static boolean processRequest(ApiVM req) { // Handles the API request lifecycle.
        var q = quotas.get(req.subId()); // 1. Retrieve static quota (as PPhani confirmed it doesn't change).
        if (q == null) return false; // Reject if subscription doesn't exist.

        var state = states.computeIfAbsent(req.subId(), k -> new SubState()); // Get or create the live state tracker.

        // Step 1: Microsecond-fast reservation.
        if (!state.reserve(req, q)) return false; // If quota is full, reject immediately.

        // Step 2: Slow validation (Runs OUTSIDE the lock, allowing parallel processing)
        if (!validateInput(req)) { // Check if OS is valid, etc.
            state.rollback(req); // PPhani's requirement: If validation fails, refund the quota!
            return false; // Return failure to the user.
        }

        // Step 3: Slow Database Persistence (Runs OUTSIDE the lock)
        if (!persistVM(req)) { // Try to save to DB.
            state.rollback(req); // PPhani's requirement: If DB fails, refund the quota!
            return false; // Return failure to the user.
        }

        return true; // Everything passed, VM is permanently created.
    }

    // 5. Simulated Slow Methods (Taking hundreds of ms, as requested)
    static boolean validateInput(ApiVM input) { // Simulates complex OS checks.
        try { Thread.sleep(100); } catch (InterruptedException e) {} // Simulates taking hundreds of ms.
        return !input.os().equals("FakeOS"); // Simulates a validation failure scenario.
// Valid input.
    }

    static boolean persistVM(ApiVM input) { // Simulates database saving.
        try { Thread.sleep(100); } catch (InterruptedException e) {} // Simulates taking hundreds of ms.
        return !input.vmName().contains("FailDB"); // Simulates a database crash scenario.
// Successfully saved.
    }

    // 6. Testing (Using main method, Java 21 Virtual Threads, NO JUnit)
    public static void main(String[] args) throws Exception { // Main execution block.
        System.out.println("Running Tests..."); // Start tests.

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { // Java 21 ultra-lightweight thread pool for massive concurrency.

            // TEST 1: PPhani's Failure Scenario - Validation Fails
            // We request 4 valid VMs and 1 invalid VM. The invalid one should fail and NOT consume quota.
            var futures1 = new ArrayList<Future<Boolean>>(); // List to track tasks.
            futures1.add(executor.submit(() -> processRequest(new ApiVM("SubA", "VM-Bad", 2, "FakeOS")))); // This will fail validation.
            for (int i = 0; i < 4; i++) { // Loop to add 4 valid requests.
                var req = new ApiVM("SubA", "VM-" + i, 2, "Windows"); // Valid payload.
                futures1.add(executor.submit(() -> processRequest(req))); // Submit valid requests concurrently.
            }

            int success1 = 0; // Counter for successes.
            for (var f : futures1) if (f.get()) success1++; // Tally up successful creations.

            // Because the "FakeOS" rolled back, there should be exactly 4 successes, filling the quota perfectly.
            System.out.println("Test 1 (Validation Failure & Rollback): " + (success1 == 4 ? "PASS" : "FAIL")); // Verify output.


            // TEST 2: PPhani's Failure Scenario - Persistence Fails
            states.clear(); // Reset state.
            var futures2 = new ArrayList<Future<Boolean>>(); // List to track tasks.
            futures2.add(executor.submit(() -> processRequest(new ApiVM("SubA", "FailDB-VM", 2, "Windows")))); // This will fail DB.
            for (int i = 0; i < 4; i++) { // Loop to add 4 valid requests.
                var req = new ApiVM("SubA", "VM-" + i, 2, "Windows"); // Valid payload.
                futures2.add(executor.submit(() -> processRequest(req))); // Submit valid requests concurrently.
            }

            int success2 = 0; // Counter for successes.
            for (var f : futures2) if (f.get()) success2++; // Tally up successful creations.

            // Because "FailDB-VM" rolled back, the other 4 succeed.
            System.out.println("Test 2 (Persistence Failure & Rollback): " + (success2 == 4 ? "PASS" : "FAIL")); // Verify output.


            // TEST 3: High Concurrency Success (The 5000 VM requirement)
            states.clear(); // Reset state for enterprise test.
            var futures3 = new ArrayList<Future<Boolean>>(); // List to track tasks.
            for (int i = 0; i < 5000; i++) { // Massive loop.
                var req = new ApiVM("SubBig", "App-" + i, 4, "Linux"); // Enterprise payload.
                futures3.add(executor.submit(() -> processRequest(req))); // Submit 5000 simultaneously.
            }

            int success3 = 0; // Counter for successes.
            for (var f : futures3) if (f.get()) success3++; // Tally up successful creations.

            // All 5000 should pass because we process them concurrently outside the lock.
            System.out.println("Test 3 (5000 Concurrent Requests): " + (success3 == 5000 ? "PASS" : "FAIL")); // Verify output.
        }
    }

    // 1. Data Structures using Java 21 Records (Simple, immutable, zero boilerplate)
    record ApiVM(String subId, String vmName, int cores, String os) {} // Matches the VM properties PPhani mentioned.

    record Quota(int maxVms, int maxCores) {} // Holds the static limits retrieved from the database.

    // 2. State Tracker (The lock is ONLY held for microseconds here, NOT during validation)
    static class SubState { // Tracks live, inflight usage for a single subscription.
        int usedVms = 0; // Current number of VMs successfully created or currently processing.
        int usedCores = 0; // Current number of cores successfully created or currently processing.
        Set<String> vms = new HashSet<>(); // Tracks VM names to prevent exact duplicates.

        // synchronized locks ONLY this specific customer's state for a fraction of a millisecond.
        synchronized boolean reserve(ApiVM vm, Quota q) { // Called BEFORE validation to claim space.
            if (vms.contains(vm.vmName())) return false; // Fail fast if name already exists.
            if (usedVms + 1 > q.maxVms()) return false; // Fail fast if VM count exceeds quota.
            if (usedCores + vm.cores() > q.maxCores()) return false; // Fail fast if CPU count exceeds quota.

            usedVms++; // Temporarily claim the VM count.
            usedCores += vm.cores(); // Temporarily claim the CPU cores.
            vms.add(vm.vmName()); // Temporarily claim the VM name.
            return true; // Reservation successful, safe to proceed to slow validation.
        }

        // synchronized ensures we safely refund the quota if slow operations fail.
        synchronized void rollback(ApiVM vm) { // Called if validation or DB persistence fails later.
            usedVms--; // Refund the VM count so the customer can try again later.
            usedCores -= vm.cores(); // Refund the CPU cores.
            vms.remove(vm.vmName()); // Free up the VM name.
        }
    }
}