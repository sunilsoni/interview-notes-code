package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CartTrayMapperJava17 {

    /**
     * Converts List<Cart> -> Map<CartId, Sorted List<TrayId>>
     * - Handles null carts
     * - Handles null trays list per cart
     * - Ignores null tray objects and null tray IDs
     * - Sorts tray IDs ascending
     */
    public static Map<Long, List<Long>> toCartIdToSortedTrayIds(List<Cart> carts) {
        // If input list itself is null, safely return an empty map to avoid NPE.
        if (carts == null) return Collections.emptyMap(); // guard against null input

        // Stream over carts to build the desired map.
        return carts.stream()                                // create a stream of Cart
                .filter(Objects::nonNull)                    // skip null cart entries to avoid NPE
                .collect(Collectors.toMap(                   // collect into a Map<CartId, List<TrayId>>
                        Cart::id,                            // key mapper -> cart id (record accessor)
                        cart -> Optional.ofNullable(cart.trays())   // handle possible null trays list
                                .orElse(Collections.emptyList())     // if null, use empty list so stream works
                                .stream()                           // stream over trays
                                .filter(Objects::nonNull)           // ignore null tray entries
                                .map(Tray::id)                      // extract tray id (may be null)
                                .filter(Objects::nonNull)           // ignore null tray IDs
                                .sorted()                           // sort tray IDs in ascending order
                                .collect(Collectors.toList()),      // collect into a list
                        (a, b) -> {                                 // merge function if duplicate cart IDs appear
                            // If duplicate Cart IDs exist, merge the two lists safely.
                            // We also keep them sorted after merge.
                            List<Long> merged = new ArrayList<>(a); // start with first list
                            merged.addAll(b);                       // add second list
                            Collections.sort(merged);               // sort merged result
                            return merged;                          // return merged sorted list
                        },
                        LinkedHashMap::new                          // preserve encounter order of carts in map
                ));
    }

    // Utility method: pretty compare two maps of Long -> List<Long> for equality
    private static boolean mapEquals(Map<Long, List<Long>> a, Map<Long, List<Long>> b) {
        // Quick reference check
        if (a == b) return true;                                        // if same object, equal
        if (a == null || b == null || a.size() != b.size()) return false; // mismatch in null/size => not equal
        // Compare each key and list content (order matters since trays are sorted)
        for (Map.Entry<Long, List<Long>> e : a.entrySet()) {            // iterate entries of 'a'
            List<Long> other = b.get(e.getKey());                       // get corresponding list in 'b'
            if (other == null) return false;                            // key missing in 'b'
            if (!Objects.equals(e.getValue(), other)) return false;     // list content not equal
        }
        return true;                                                    // all entries matched
    }

    // Helper to print PASS/FAIL with a label
    private static void assertEquals(String label, Map<Long, List<Long>> expected, Map<Long, List<Long>> actual) {
        boolean ok = mapEquals(expected, actual);                       // check structural equality
        System.out.println(label + " -> " + (ok ? "PASS" : "FAIL"));    // print status
        if (!ok) {                                                      // if failed, show diffs
            System.out.println("  Expected: " + expected);              // expected map
            System.out.println("  Actual  : " + actual);                // actual map
        }
    }

    public static void main(String[] args) {
        // --------- Small, focused tests ---------

        // Build sample trays
        Tray t1 = new Tray(3L, "T3");           // tray with id 3
        Tray t2 = new Tray(1L, "T1");           // tray with id 1
        Tray t3 = new Tray(2L, "T2");           // tray with id 2
        Tray tNullId = new Tray(null, "Null");  // tray with null id (should be ignored)

        // Build carts with various edge cases
        Cart c1 = new Cart(101L, "CartA", Arrays.asList(t1, t2, t3, tNullId)); // mixed + null id to ignore
        Cart c2 = new Cart(102L, "CartB", null);                                // null trays => empty list in map
        Cart c3 = new Cart(103L, "CartC", Arrays.asList(new Tray(5L, "T5")));   // single tray
        Cart c4 = null;                                                         // null cart in list (ignored)
        Cart c5 = new Cart(101L, "CartA-duplicateId", Arrays.asList(new Tray(4L, "T4"))); // duplicate ID => merge

        // Input list containing all cases
        List<Cart> input = Arrays.asList(c1, c2, c3, c4, c5); // includes null cart and duplicate ID

        // Execute conversion
        Map<Long, List<Long>> result = toCartIdToSortedTrayIds(input); // run mapping

        // Expected map after handling all rules:
        Map<Long, List<Long>> expected = new LinkedHashMap<>(); // preserve insertion order for readability
        expected.put(101L, Arrays.asList(1L, 2L, 3L, 4L));      // from c1 trays [1,2,3] + merged [4]
        expected.put(102L, Collections.emptyList());            // null trays -> []
        expected.put(103L, Collections.singletonList(5L));      // single tray [5]

        // Validate small test
        assertEquals("Test 1: Mixed edge cases + duplicate cartId merge + sorted trayIds", expected, result);

        // --------- Another sanity test: empty input ---------
        Map<Long, List<Long>> resultEmpty = toCartIdToSortedTrayIds(Collections.emptyList()); // empty list
        assertEquals("Test 2: Empty input", Collections.emptyMap(), resultEmpty);             // expect empty map

        // --------- Test with only null lists and null trays ---------
        Cart c6 = new Cart(201L, "OnlyNullTrays", null);          // null trays
        Cart c7 = new Cart(202L, "NullTrayObjects", Arrays.asList(null, null)); // trays list has null entries
        Map<Long, List<Long>> resultNulls = toCartIdToSortedTrayIds(Arrays.asList(c6, c7)); // run mapping
        Map<Long, List<Long>> expectedNulls = new LinkedHashMap<>(); // expected
        expectedNulls.put(201L, Collections.emptyList());         // null trays => []
        expectedNulls.put(202L, Collections.emptyList());         // only null tray entries => []
        assertEquals("Test 3: Null trays and null tray entries", expectedNulls, resultNulls); // validate

        // --------- Large data test for performance and correctness ---------
        // We create N carts, each with M trays (random ids), and verify sorting & sizes quickly.
        int N = 5000;                                              // number of carts
        int M = 4;                                                 // trays per cart
        List<Cart> largeInput = IntStream.range(0, N)              // create 0..N-1
                .mapToObj(i -> {
                    // For each cart, build M trays with random ids (allow duplicates randomly)
                    List<Tray> trays = IntStream.range(0, M)
                            .mapToObj(j -> new Tray(
                                    ThreadLocalRandom.current().nextLong(1, 1_000_000), // random id in range
                                    "T" + j))                                           // simple name
                            .toList();                                                  // collect to immutable list (Java 16+)
                    return new Cart(1000L + i, "Cart-" + i, trays);                     // create cart i
                })
                .toList();                                                              // collect to immutable list

        long start = System.nanoTime();                               // start timing
        Map<Long, List<Long>> largeResult = toCartIdToSortedTrayIds(largeInput); // run mapping
        long elapsedMicros = (System.nanoTime() - start) / 1_000;     // compute time in microseconds

        // Quick verification: number of keys and each value list size equals M (since no nulls here)
        boolean sizeOk = (largeResult.size() == N)                    // confirm N carts
                && largeResult.values().stream().allMatch(lst -> lst.size() == M); // each has M tray IDs

        // Also verify each list is sorted ascending
        boolean sortedOk = largeResult.values().stream()
                .allMatch(lst -> IntStream.range(1, lst.size()).allMatch(k -> lst.get(k - 1) <= lst.get(k)));

        System.out.println("Test 4: Large dataset (N=" + N + ", M=" + M + ", ~" + (N * M) + " trays) -> "
                + ((sizeOk && sortedOk) ? "PASS" : "FAIL") + " | Time: " + elapsedMicros + " μs");
    }

    // record defines an immutable data carrier with auto-generated ctor, accessors, equals/hashCode, toString
    // Using records eliminates boilerplate getters/setters.
    public record Tray(Long id, String name) {
    }  // Tray has an id and name; accessors are id() and name()

    public record Cart(Long id, String name, List<Tray> trays) {
    } // Cart has id, name, and a list of trays
}