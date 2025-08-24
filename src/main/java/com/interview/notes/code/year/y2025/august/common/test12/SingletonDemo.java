package com.interview.notes.code.year.y2025.august.common.test12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Alternative idiomatic singleton using enum.
 * Pros: simplest, serialization-safe, guard against reflection.
 * Cons: not strictly lazy (instance is created when enum class is loaded).
 */
enum EnumSingleton {                                 // Enum guarantees single instance per enum constant
    INSTANCE;                                        // The one-and-only instance

    public void doSomething() {                      // Sample API method on enum singleton
        System.out.println("Enum singleton action from " + System.identityHashCode(this));
    }
}

/**
 * Primary, lazy, thread-safe Singleton implemented with Double-Checked Locking (DCL).
 */
final class LazySingletonDCL {                       // 'final' prevents subclassing which can break singleton
    private static volatile LazySingletonDCL INSTANCE; // 'volatile' prevents instruction reordering and ensures visibility

    private LazySingletonDCL() {                      // Private constructor blocks external instantiation
        // Simulate some construction work (optional) to widen the race window if you like:
        // try { Thread.sleep(1); } catch (InterruptedException ignored) {}
    }

    public static LazySingletonDCL getInstance() {   // Global access point to the single instance
        LazySingletonDCL local = INSTANCE;           // Read volatile field into local for performance (one volatile read)
        if (local == null) {                         // First check without locking (fast path after initialization)
            synchronized (LazySingletonDCL.class) {  // Lock only when we *think* we must create
                local = INSTANCE;                    // Re-read inside synchronized block to avoid lost initialization
                if (local == null) {                 // Second check ensures only one thread actually constructs
                    local = new LazySingletonDCL();  // Create the singleton instance
                    INSTANCE = local;                // Publish to the volatile field (safe publication)
                }
            }
        }
        return local;                                // Return the (now definitely initialized) instance
    }

    public void doSomething() {                      // Sample API method to prove the instance works
        System.out.println("Doing something from " + System.identityHashCode(this));
    }
}

/**
 * Demo + tests in a simple main method (no JUnit).
 * Shows: correctness under concurrency, stream-based checks, and a "large data" style loop.
 */
public class SingletonDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException { // Declare throws for simplicity
        // ---------- Test 1: Basic usage ----------
        System.out.println("Test 1: Basic doSomething()");
        LazySingletonDCL.getInstance().doSomething();  // Should print once, any number of calls are allowed
        LazySingletonDCL.getInstance().doSomething();  // Call again to show reuse of same instance

        // ---------- Test 2: Concurrency correctness (identity uniqueness) ----------
        System.out.println("\nTest 2: Concurrency race on getInstance()");
        final int threads = 200;                       // Number of concurrent tasks
        final int callsPerThread = 50;                 // Each task calls getInstance multiple times

        var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // Fixed thread pool sized to CPUs

        // Prepare a list of tasks; each returns the identityHashCode of the instance it observed
        List<Callable<Integer>> tasks = IntStream.range(0, threads)  // Stream from 0 to threads-1
                .mapToObj(i -> (Callable<Integer>) () -> {           // Convert each int to a Callable<Integer>
                    int last = -1;                                   // Track the last observed identity (sanity check)
                    for (int c = 0; c < callsPerThread; c++) {       // Do several calls per task to increase contention
                        LazySingletonDCL inst = LazySingletonDCL.getInstance(); // Acquire instance
                        int id = System.identityHashCode(inst);       // Obtain a stable identity for comparison
                        if (last != -1 && last != id) {               // If identity changes within a task, it's a failure
                            throw new IllegalStateException("Instance identity changed within task!");
                        }
                        last = id;                                    // Update last seen identity
                    }
                    return last;                                      // Return the identity seen by this task
                })
                .collect(Collectors.toList());                        // Collect all tasks into a list

        // Submit all tasks and collect futures
        List<Future<Integer>> futures = pool.invokeAll(tasks);        // Run all tasks concurrently and wait for completion

        // Extract identities from futures
        List<Integer> identities = new ArrayList<>();                 // Will store all reported identities
        for (Future<Integer> f : futures) {                           // Iterate over concurrent results
            identities.add(f.get());                                  // get() is safe now (invokeAll waited), add to list
        }

        // Check uniqueness: all identities must be equal => Set size == 1
        Set<Integer> unique = identities.stream().collect(Collectors.toSet()); // Convert to Set to measure uniqueness
        boolean passConcurrency = (unique.size() == 1);               // True iff only one unique identity exists
        System.out.println("Unique identities: " + unique.size() + " -> " + (passConcurrency ? "PASS" : "FAIL"));

        // ---------- Test 3: Large volume access (performance smoke test) ----------
        System.out.println("\nTest 3: Large volume calls");
        final long start = System.nanoTime();                         // Start timing
        final int bigN = 1_000_000;                                   // One million calls as a quick stress test
        for (int i = 0; i < bigN; i++) {                              // Tight loop calling getInstance()
            if (LazySingletonDCL.getInstance() == null) {             // Defensive: should never be null after init
                throw new AssertionError("Instance unexpectedly null!");
            }
        }
        final long elapsedMicros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start); // Convert elapsed to microseconds
        System.out.println("Performed " + bigN + " getInstance() calls in ~" + elapsedMicros + " µs -> PASS");

        // ---------- Test 4: Enum singleton demo (not lazy, just for reference) ----------
        System.out.println("\nTest 4: Enum singleton demo");
        EnumSingleton.INSTANCE.doSomething();                         // Show enum works too

        // Cleanup the pool
        pool.shutdown();                                              // Politely request pool shutdown
        pool.awaitTermination(5, TimeUnit.SECONDS);                   // Wait for completion (best-effort)

        // ---------- Final verdict ----------
        boolean allPass = passConcurrency;                            // Aggregate pass flags (others throw on failure)
        System.out.println("\nOVERALL: " + (allPass ? "PASS" : "FAIL"));
    }
}