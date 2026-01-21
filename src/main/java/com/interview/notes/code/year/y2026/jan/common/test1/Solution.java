package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.*;

interface Task { // Defines the contract we must follow.
    List<Task> deps(); // Returns tasks that must run before this task.

    void exec(); // Runs this task (deps must already be executed).
}

public class Solution { // Container class requested in the prompt.

    static void executeAll(List<Task> tasks) { // Executes all tasks respecting dependency order.
        if (tasks == null || tasks.isEmpty()) return; // Nothing to do for null/empty input.

        Map<Task, Integer> state = new IdentityHashMap<>(); // Tracks 0/1/2 state per task by identity (safe if equals/hashCode not defined).
        List<Task> order = new ArrayList<>(); // Stores tasks in a valid execution order.

        record Frame(Task t, List<Task> ds, int i) {
        } // Stack frame: current task, its deps list, and next dep index to process.

        Deque<Frame> stack = new ArrayDeque<>(); // Explicit stack to avoid recursion (prevents stack overflow on large graphs).

        for (Task root : tasks) { // We must process every input task (and its deps).
            if (root == null) continue; // Ignore null tasks to be safe.

            if (state.getOrDefault(root, 0) != 0) continue; // If already seen (visiting/done), skip starting DFS again.

            List<Task> rootDeps = safeDeps(root); // Normalize deps to a non-null list.
            state.put(root, 1); // Mark as visiting (in current DFS path).
            stack.push(new Frame(root, rootDeps, 0)); // Start DFS for this root.

            while (!stack.isEmpty()) { // Continue until the current DFS finishes.
                Frame f = stack.peek(); // Look at the current frame without removing it yet.
                Task cur = f.t(); // The task we are currently expanding.

                if (f.i() >= f.ds().size()) { // If all dependencies have been processed...
                    stack.pop(); // ...we can finish this task's DFS frame.
                    state.put(cur, 2); // Mark task as done (fully processed).
                    order.add(cur); // Add to execution order AFTER deps => guarantees correct ordering.
                    continue; // Move to next frame.
                }

                Task dep = f.ds().get(f.i()); // Get the next dependency to process.
                stack.pop(); // We will update the frame index, so remove it...
                stack.push(new Frame(cur, f.ds(), f.i() + 1)); // ...and push back same frame with i+1.

                if (dep == null) continue; // Ignore null dependency safely.

                int depState = state.getOrDefault(dep, 0); // Read current state of dependency.

                if (depState == 2) continue; // If dependency already done, nothing to do.

                if (depState == 1) { // If dependency is in current DFS path...
                    throw new IllegalStateException("Cycle detected in task dependencies"); // ...it's a cycle; contract cannot be satisfied.
                }

                List<Task> depDeps = safeDeps(dep); // Normalize dependency's deps list.
                state.put(dep, 1); // Mark dependency as visiting.
                stack.push(new Frame(dep, depDeps, 0)); // DFS into dependency before finishing current task.
            }
        }

        for (Task t : order) { // Execute tasks in computed valid order.
            t.exec(); // Safe because every task appears after its deps in 'order'.
        }
    }

    private static List<Task> safeDeps(Task t) { // Helper to avoid null checks everywhere.
        List<Task> ds = t.deps(); // Read deps from task.
        return ds == null ? List.of() : ds; // Convert null to empty list for stable logic.
    }

    // ------------------------ TEST HARNESS (no JUnit) ------------------------

    static boolean validateOrder(String log, Map<String, List<String>> depsMap) { // Validates "deps happen before task" and "no duplicates".
        String[] parts = log.trim().isEmpty() ? new String[0] : log.trim().split("\\s+"); // Split log into executed task names.
        Map<String, Integer> pos = new HashMap<>(); // Stores position of each executed task.
        for (int i = 0; i < parts.length; i++) { // Walk execution list.
            if (pos.putIfAbsent(parts[i], i) != null)
                return false; // If already present => duplicate execution => invalid.
        }
        for (var e : depsMap.entrySet()) { // For each task and its dependencies...
            Integer taskPos = pos.get(e.getKey()); // Find executed position of the task.
            if (taskPos == null) continue; // If task not executed, ignore (depends on test definition).
            for (String d : e.getValue()) { // Check each dependency...
                Integer depPos = pos.get(d); // Find executed position of dependency.
                if (depPos == null) return false; // If dep never executed => invalid.
                if (depPos > taskPos) return false; // If dep executed after task => invalid.
            }
        }
        return true; // All checks passed.
    }

    public static void main(String[] args) { // Entry point for running tests manually.
        runTest_basicSharedDeps(); // Runs example-style test.
        runTest_duplicatesInInput(); // Ensures duplicates don't cause double exec.
        runTest_cycle(); // Ensures cycle detection works.
        runTest_largeChain(); // Ensures large input works without recursion overflow.
    }

    static void runTest_basicSharedDeps() { // Test: shared deps across multiple tasks.
        StringBuilder log = new StringBuilder(); // Create log for this test.

        T b = new T("b", List.of(), log); // b has no deps.
        T c = new T("c", List.of(), log); // c has no deps.
        T a = new T("a", List.of(b, c), log); // a depends on b and c.
        T d = new T("d", List.of(b, c), log); // d depends on b and c (shared deps).

        executeAll(List.of(a, b, c, d)); // Execute as prompt example suggests.

        Map<String, List<String>> depsMap = Map.of( // Dependency rules we must satisfy.
                "a", List.of("b", "c"),
                "d", List.of("b", "c"),
                "b", List.of(),
                "c", List.of()
        );

        boolean ok = validateOrder(log.toString(), depsMap); // Validate correctness (not exact order).
        System.out.println("TEST basicSharedDeps: " + (ok ? "PASS" : "FAIL") + " | " + log); // Print PASS/FAIL and the order.
    }

    static void runTest_duplicatesInInput() { // Test: same task repeated in input list.
        StringBuilder log = new StringBuilder(); // Create log.

        T x = new T("x", List.of(), log); // Task with no deps.
        executeAll(List.of(x, x, x)); // Same task repeated.

        boolean ok = log.toString().trim().equals("x"); // Must execute only once.
        System.out.println("TEST duplicatesInInput: " + (ok ? "PASS" : "FAIL") + " | " + log); // Print result.
    }

    static void runTest_cycle() { // Test: cycle should throw.
        StringBuilder log = new StringBuilder(); // Create log.

        T[] holder = new T[2]; // Small trick to create cyclic references.

        T a = new T("a", new ArrayList<>(), log); // Create a with mutable deps list.
        T b = new T("b", new ArrayList<>(), log); // Create b with mutable deps list.

        a.deps.add(b); // a depends on b.
        b.deps.add(a); // b depends on a => cycle.

        boolean ok; // Tracks pass/fail.
        try {
            executeAll(List.of(a)); // Should throw due to cycle.
            ok = false; // If no exception, test fails.
        } catch (IllegalStateException ex) {
            ok = true; // Expected behavior.
        }
        System.out.println("TEST cycle: " + (ok ? "PASS" : "FAIL") + " | " + log); // Print result.
    }

    static void runTest_largeChain() { // Test: large deep chain should not stack overflow.
        StringBuilder log = new StringBuilder(); // Create log.

        int n = 100_000; // Large size to stress recursion risk.
        T prev = new T("t0", List.of(), log); // First task has no deps.
        List<T> all = new ArrayList<>(n); // Store all tasks.
        all.add(prev); // Add first.

        for (int i = 1; i < n; i++) { // Build a chain: ti depends on t(i-1).
            T cur = new T("t" + i, List.of(prev), log); // Create current with dependency on previous.
            all.add(cur); // Save it.
            prev = cur; // Move forward.
        }

        executeAll(List.of(all.get(n - 1))); // Execute only the last; should pull in all deps.

        boolean ok = log.length() > 0; // Basic sanity check that something ran.
        System.out.println("TEST largeChain: " + (ok ? "PASS" : "FAIL") + " | executed=" + (log.toString().trim().split("\\s+").length)); // Print count.
    }

    /**
     * @param name Name helps identify tasks in logs and debug output.
     * @param deps Dependency list for this task.
     * @param log  Shared log to record execution order.
     */
    record T(String name, List<Task> deps,
             StringBuilder log) implements Task { // Simple Task implementation for testing.
        // Constructor to build test tasks.
        // Store name.
        // Store deps (may be empty).
        // Store shared log reference.

        public void exec() {
            log.append(name).append(' ');
        } // Record execution in order (space-separated).

        public String toString() {
            return name;
        } // Useful for debugging messages.
    }
}
