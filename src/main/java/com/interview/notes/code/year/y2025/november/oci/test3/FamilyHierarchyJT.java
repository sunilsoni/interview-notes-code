package com.interview.notes.code.year.y2025.november.oci.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FamilyHierarchyJT {                  // Main class holding solution and tests

    // Public API required by the prompt: find all ancestors for 'person' in the specified order
    public static List<String> findAncestors(int n, List<List<String>> relationships, String person) {
        // Create a map: child -> father for O(1) parent lookup
        Map<String, String> fatherOf = new HashMap<>();
        // Create a map: child -> mother for O(1) parent lookup
        Map<String, String> motherOf = new HashMap<>();

        // Fill the maps from the input relationships using a simple for-each loop (clear and fast)
        for (List<String> triplet : relationships) {         // Each triplet = [father, mother, child]
            String father = triplet.get(0);                  // Read father name at index 0
            String mother = triplet.get(1);                  // Read mother name at index 1
            String child  = triplet.get(2);                  // Read child  name at index 2
            fatherOf.put(child, father);                     // Record father for that child
            motherOf.put(child, mother);                     // Record mother for that child
        }

        // Output list to collect ancestors in exact required order
        List<String> result = new ArrayList<>();
        // A 'visited' set prevents infinite loops if bad/cyclic data appears
        Set<String> visited = new HashSet<>();

        // Kick off the recursive collection from the queried person
        collectAncestors(person, fatherOf, motherOf, visited, result);

        // Return the list after traversal finishes
        return result;
    }

    // Recursive helper that appends ancestors in order: father's ancestors, father, mother's ancestors, mother
    private static void collectAncestors(
            String person,                               // The person whose parents we are exploring
            Map<String, String> fatherOf,                // Map to find father quickly
            Map<String, String> motherOf,                // Map to find mother quickly
            Set<String> visited,                         // Protect against cycles / duplicate expansions
            List<String> out) {                          // Output list to append names in order

        if (person == null) return;                      // If no person provided, nothing to do

        String father = fatherOf.get(person);            // Lookup father (may be null if unknown)
        String mother = motherOf.get(person);            // Lookup mother (may be null if unknown)

        // --- Handle father's branch first (as per required order) ---
        if (father != null) {                            // If father exists, explore his ancestors first
            if (visited.add(father)) {                   // Add father to visited; returns false if already seen
                collectAncestors(father, fatherOf, motherOf, visited, out); // Recurse into father's branch
                out.add(father);                         // After father's ancestors, append father's name
            }
            // If father was already visited, we skip to avoid duplicates/cycles
        }

        // --- Handle mother's branch second (as per required order) ---
        if (mother != null) {                            // If mother exists, explore her ancestors
            if (visited.add(mother)) {                   // Mark mother as visited to avoid re-processing
                collectAncestors(mother, fatherOf, motherOf, visited, out); // Recurse into mother's branch
                out.add(mother);                         // After mother's ancestors, append mother's name
            }
            // If mother was already visited, we skip to avoid duplicates/cycles
        }
    }

    // ---------- Simple Test Harness with a main method (no JUnit) ----------
    public static void main(String[] args) {
        // Helper lambda to create a triplet [father, mother, child] succinctly
        java.util.function.Function<String[], List<String>> T =
                arr -> Arrays.stream(arr).collect(Collectors.toList()); // Use Stream API to build a List

        // -------- Test 1: Provided Sample --------
        List<List<String>> rel1 = Arrays.asList(          // Relationships for sample input
                T.apply(new String[]{"Ronald","Paula","Jason"}),
                T.apply(new String[]{"Travis","Judy","Mary"}),
                T.apply(new String[]{"Jason","Mary","Benjamin"}),
                T.apply(new String[]{"Homer","Marge","Bart"}),
                T.apply(new String[]{"Ervin","Marie","Paula"}),
                T.apply(new String[]{"Clancy","Jacky","Marge"})
        );

        // Expected output for person "Benjamin" based on the problem statement
        List<String> exp1 = Arrays.asList("Ronald","Ervin","Marie","Paula","Jason","Travis","Judy","Mary");

        // Run and check
        runTest("Sample Benjamin", rel1, "Benjamin", exp1);

        // -------- Test 2: Another person from the same data (Bart) --------
        List<String> exp2 = Arrays.asList("Homer","Clancy","Jacky","Marge"); // Father's branch then mother’s
        runTest("Bart simple", rel1, "Bart", exp2);

        // -------- Test 3: Person with no parents listed (root) --------
        List<String> exp3 = Collections.emptyList();      // No ancestors should be returned
        runTest("Ronald root", rel1, "Ronald", exp3);

        // -------- Test 4: Duplicate name safety / cycle guard --------
        // Create a tiny, intentionally cyclic graph: A's father is B; B's father is A
        List<List<String>> relCycle = Arrays.asList(
                T.apply(new String[]{"B","M1","A"}),      // A <- (B,M1)
                T.apply(new String[]{"A","M2","B"})       // B <- (A,M2)  (cycle via father)
        );
        // With visited guard we should terminate and list each parent once, order father first then mother
        List<String> gotCycle = findAncestors(2, relCycle, "A");      // Execute
        System.out.println("Cycle guard result (A): " + gotCycle);    // Show the safe, finite output

        // -------- Test 5: Large data test (performance, shallow depth) --------
        // Build many unrelated families to test map building speed and memory behavior
        int FAMILIES = 100_000;                                       // 100k triples
        List<List<String>> bigFlat = IntStream.range(0, FAMILIES)     // Use Stream API to generate data
                .mapToObj(i -> T.apply(new String[]{
                        "F"+i, "M"+i, "C"+i                              // Each child Ci has parents Fi and Mi
                }))
                .collect(Collectors.toList());                        // Collect to List<List<String>>
        long t1 = System.nanoTime();                                  // Start timer
        List<String> gotFlat = findAncestors(bigFlat.size(), bigFlat, "C54321"); // Query one child
        long t2 = System.nanoTime();                                  // End timer
        System.out.println("Large(flat) ancestors count=" + gotFlat.size()
                + " timeMs=" + ((t2 - t1)/1_000_000));                // Expect 2 names, very fast

        // -------- Test 6: Large balanced tree (many ancestors, safe depth) --------
        // We synthesize a 2-ary "upward" tree: for i in [1..L], child=Ci, father=C(2i), mother=C(2i+1)
        int L = 65_000;                                               // ~65k relationships; depth ~16
        List<List<String>> bigTree = IntStream.rangeClosed(1, L)
                .mapToObj(i -> T.apply(new String[]{
                        "C"+(2*i), "C"+(2*i+1), "C"+i                     // Define parents for Ci
                }))
                .collect(Collectors.toList());                        // Build the big tree list
        long t3 = System.nanoTime();                                  // Start timer
        List<String> gotTree = findAncestors(bigTree.size(), bigTree, "C1"); // Query root child C1
        long t4 = System.nanoTime();                                  // End timer
        System.out.println("Large(tree) ancestors count=" + gotTree.size()
                + " timeMs=" + ((t4 - t3)/1_000_000));                // Many names, still fast

        // Final line so it’s easy to spot that the test run is complete
        System.out.println("All tests executed.");
    }

    // Small helper to run a single test and print PASS/FAIL clearly
    private static void runTest(String name, List<List<String>> relationships, String person, List<String> expected) {
        // Execute the function under test
        List<String> got = findAncestors(relationships.size(), relationships, person);
        // Compare lists exactly (order-sensitive) and compute boolean result
        boolean pass = got.equals(expected);
        // Prepare a compact diff-friendly string for both expected and actual using Stream API
        String expStr = expected.stream().collect(Collectors.joining(","));
        String gotStr = got.stream().collect(Collectors.joining(","));
        // Print result with PASS/FAIL and the compared sequences
        System.out.println((pass ? "PASS  " : "FAIL  ") + name + " | expected=[" + expStr + "] got=[" + gotStr + "]");
    }
}
