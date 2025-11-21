package com.interview.notes.code.year.y2025.august.oracle.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllPairsSumX {                              // Main public class to hold solutions and test harness

    /**
     * Primary solution: HashSet + Streams (O(n) average time).
     * Emits each unordered pair once by enforcing a < b.
     */
    public static List<Pair> findPairsWithSet(int[] arr, int X) {                 // Public method to get all pairs via set
        // Build a Set<Integer> of all elements for O(1)-ish membership checks
        Set<Integer> set = Arrays.stream(arr)                                      // Create an IntStream over the array
                .boxed()                                          // Box to Stream<Integer> because Set is of Integer
                .collect(Collectors.toCollection(HashSet::new));  // Collect into a HashSet for fast lookup

        // Stream over the array, test complement existence, and enforce a<b to avoid duplicates
        List<Pair> pairs = Arrays.stream(arr)                                      // Stream primitives for performance
                .filter(a -> {                                    // Filter to keep only valid 'a' that form a pair
                    int b = X - a;                                // Compute complement
                    return set.contains(b) && a < b;              // Keep when complement exists and a<b (avoid dup/self)
                })
                .mapToObj(a -> new Pair(a, X - a))                // Map surviving 'a' into Pair(a, b)
                .sorted(Comparator.<Pair>comparingInt(p -> p.a)   // Sort by 'a' to keep output deterministic
                        .thenComparingInt(p -> p.b))
                .collect(Collectors.toList());                    // Collect all pairs into a List

        return pairs;                                                              // Return the list of unique pairs
    }

    /**
     * Alternative solution: sort + two pointers (O(n log n)).
     */
    public static List<Pair> findPairsTwoPointer(int[] arr, int X) {              // Two-pointer alternative
        int[] a = Arrays.copyOf(arr, arr.length);                                  // Copy to avoid mutating the caller's array
        Arrays.sort(a);                                                            // Sort the copy in ascending order
        List<Pair> res = new ArrayList<>();                                        // Prepare result list
        int i = 0, j = a.length - 1;                                               // Initialize two pointers at both ends
        while (i < j) {                                                            // Loop while a valid window exists
            int sum = a[i] + a[j];                                                 // Compute sum of current pair
            if (sum == X) {                                                        // If sum matches target
                res.add(new Pair(a[i], a[j]));                                     // Record the pair (already a<b due to sorting)
                i++;                                                               // Move left pointer forward to find next candidate
                j--;                                                               // Move right pointer backward to continue
            } else if (sum < X) {                                                  // If sum too small
                i++;                                                               // Increase sum by moving left pointer
            } else {                                                               // If sum too large
                j--;                                                               // Decrease sum by moving right pointer
            }
        }
        return res;                                                                // Return collected pairs
    }

    // Compare two lists of pairs ignoring order by sorting them; return true if equal
    private static boolean samePairs(List<Pair> a, List<Pair> b) {                 // Utility to compare pair lists for tests
        Comparator<Pair> cmp = Comparator.<Pair>comparingInt(p -> p.a)             // Define a deterministic comparator on Pair
                .thenComparingInt(p -> p.b);
        List<Pair> c1 = new ArrayList<>(a);                                        // Copy first list
        List<Pair> c2 = new ArrayList<>(b);                                        // Copy second list
        c1.sort(cmp);                                                              // Sort both lists
        c2.sort(cmp);
        return c1.equals(c2);                                                      // Compare structural equality
    }

    // ---------- Simple helpers for testing ----------

    // Format list of pairs as string for readable test output
    private static String pairsToString(List<Pair> pairs) {                        // Pretty printer for results
        return pairs.stream()                                                      // Stream the pairs
                .map(Pair::toString)                                           // Convert each to "(a,b)"
                .collect(Collectors.joining(", "));                            // Join with commas
    }

    // ---------- MAIN with PASS/FAIL test cases (no JUnit) ----------
    public static void main(String[] args) {                                       // Entry point for manual testing
        // Define a small assertion-like runner to print PASS/FAIL
        class T {                                                                  // Local test runner class
            void run(String name, int[] arr, int X, List<Pair> expected) {         // Run one test with name, input, expected output
                long t1 = System.nanoTime();                                       // Start timing
                List<Pair> got = findPairsWithSet(arr, X);                         // Execute primary O(n) solution
                long t2 = System.nanoTime();                                       // End timing
                boolean ok = samePairs(got, expected);                             // Compare to expected pairs
                System.out.printf("%s: %s | Got: [%s] | Time: %.3f ms%n",          // Print test result line
                        name, (ok ? "PASS" : "FAIL"),
                        pairsToString(got),
                        (t2 - t1) / 1e6);
            }
        }
        T t = new T();                                                             // Instantiate the local test runner

        // ---------- Basic tests ----------
        t.run("Test 1: simple", new int[]{1, 2, 3, 4, 6, 8, 11}, 10,
                Arrays.asList(new Pair(2, 8), new Pair(4, 6)));                      // Expect (2,8) and (4,6)

        t.run("Test 2: no pairs", new int[]{0, 5, 9}, 3,
                Collections.emptyList());                                          // No numbers sum to 3

        t.run("Test 3: single pair", new int[]{0, 3, 5, 7}, 7,
                List.of(new Pair(0, 7)));                                     // Only (0,7)

        t.run("Test 4: many pairs", new int[]{1, 3, 5, 7, 9, 11}, 12,
                Arrays.asList(new Pair(1, 11), new Pair(3, 9), new Pair(5, 7)));      // Multiple pairs present

        t.run("Test 5: includes zero", new int[]{0, 1, 2, 8, 9}, 9,
                Arrays.asList(new Pair(0, 9), new Pair(1, 8)));                      // Check zero handling

        t.run("Test 6: small X", new int[]{1, 2, 4, 8}, 3,
                List.of(new Pair(1, 2)));                                     // Small target

        // ---------- Edge cases ----------
        t.run("Edge 1: empty array", new int[]{}, 10,
                Collections.emptyList());                                          // Empty input -> no pairs

        t.run("Edge 2: one element", new int[]{5}, 10,
                Collections.emptyList());                                          // Single element -> no pairs

        t.run("Edge 3: two elements sum", new int[]{2, 8}, 10,
                List.of(new Pair(2, 8)));                                     // Exactly one pair

        t.run("Edge 4: two elements no sum", new int[]{2, 9}, 10,
                Collections.emptyList());                                          // Exactly no pair

        // ---------- Cross-check with two-pointer alternative ----------
        {
            int[] arr = new int[]{1, 2, 3, 4, 6, 8, 11};                                 // Define a sample array
            int X = 10;                                                            // Target sum
            List<Pair> setAns = findPairsWithSet(arr, X);                          // Get pairs via hashing
            List<Pair> tpAns = findPairsTwoPointer(arr, X);                       // Get pairs via two-pointer
            System.out.printf("Cross-check set vs two-pointer: %s | Set=[%s] | TP=[%s]%n",
                    samePairs(setAns, tpAns) ? "PASS" : "FAIL",
                    pairsToString(setAns), pairsToString(tpAns));                  // Ensure both methods agree
        }

        // ---------- Large data performance test ----------
        {
            // Generate a large unique array [0..N-1], shuffle, and choose a random X
            final int N = 1_000_00;                                                // 100k elements (adjustable for your machine)
            int[] big = IntStream.range(0, N).toArray();                            // Fill with unique ascending numbers
            shuffleArray(big, 12345L);                                             // Shuffle deterministically with a seed
            int X = N - 1;                                                         // Pick an X that yields many pairs (i + (X-i))
            long t1 = System.nanoTime();                                           // Start timing
            List<Pair> got = findPairsWithSet(big, X);                             // Run hashing solution
            long t2 = System.nanoTime();                                           // End timing
            // For X = N-1 and array 0..N-1 unique, expected pairs count is floor(N/2)
            int expectedCount = (X < 0 || X >= 2 * (N - 1)) ? 0 : (X / 2 + 1);     // Quick count math; here ~N/2 (exact formula varies)
            boolean countOk = got.size() == (N / 2);                                // With X=N-1 and 0..N-1, it should be N/2
            System.out.printf("Large dataset: %s | pairs=%d | Time: %.3f ms%n",
                    countOk ? "PASS" : "WARN", got.size(), (t2 - t1) / 1e6);       // Print outcome and timing
        }
    }

    // Fisher–Yates shuffle for the large test; deterministic via seed for repeatability
    private static void shuffleArray(int[] a, long seed) {                          // Shuffle helper to avoid ordered bias
        Random rnd = new Random(seed);                                              // Deterministic RNG for reproducibility
        for (int i = a.length - 1; i > 0; i--) {                                    // Walk backward through the array
            int j = rnd.nextInt(i + 1);                                             // Pick random index in [0, i]
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;                                // Swap a[i] and a[j]
        }
    }

    /**
     * @param a Store first value of the pair
     * @param b Store second value of the pair
     */ // Simple immutable pair to hold results cleanly and print nicely
        record Pair(int a, int b) {                            // Define a small helper class representing a pair (a, b)
        // Constructor to set both fields

        @Override
            public String toString() {             // Override toString for readable output
                return "(" + a + "," + b + ")";              // Format pair as (a,b)
            }

    }
}