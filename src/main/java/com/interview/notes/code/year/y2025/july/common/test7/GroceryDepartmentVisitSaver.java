package com.interview.notes.code.year.y2025.july.common.test7;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * GroceryDepartmentVisitSaver
 * ---------------------------------
 * Problem:
 * You have an unsorted master catalog of products -> departments and a shopping list (ordered).
 * If you buy strictly in the given list order you may bounce between departments many times.
 * Instead, you can optimize by finishing all items from a department before moving to the next department.
 * <p>
 * Goal:
 * Return the time saved measured as the number of department visits eliminated:
 * timeSaved = (visits when following list order) - (visits when grouping by department).
 * <p>
 * Definitions:
 * A "department visit" is counted each time you newly enter a department to pick at least one item.
 * When following the shopping list order you count a visit the first time you see its department, and again
 * whenever the next item in the list belongs to a different department than the previous item.
 * (So visits = number of department runs in the list order.)
 * <p>
 * When optimizing, you will consolidate all items from the same department into a single continuous visit.
 * Thus optimized visits = number of *distinct* departments appearing among the needed items.
 * <p>
 * Edge Handling:
 * - If an item in the shopping list has no known department in the catalog, we treat that item as belonging to a
 * synthetic department named "__UNKNOWN__:<itemName>" so that each unknown item forces its own visit. This is a
 * conservative choice that does not under-estimate visits. (You may change this policy; see TODO below.)
 * - Shopping list may contain duplicates; duplicates in the same department and adjacent do NOT add visits; if separated
 * by items from other departments they do (in the list-order calculation).
 * - Empty shopping list => 0 visits => 0 time saved.
 * <p>
 * Complexity:
 * Let L = shopping list length.
 * Building item->dept lookup: O(P) where P = number of catalog entries. (Done once.)
 * Counting original visits: O(L).
 * Counting optimized visits: O(L) to collect departments then dedupe via HashSet.
 * Space: O(P + D) where D is #distinct departments encountered in the list.
 * <p>
 * Why Streams?
 * We use Java 8 streams where it aids clarity (mapping catalog to Map; gathering departments; generating tests), but we
 * keep imperative loops in performance-critical or comment-heavy sections for readability in simple language.
 * <p>
 * Usage:
 * See main() for demo tests (small, edge, and large randomized stress tests). The program prints PASS/FAIL per test.
 * <p>
 * Originality Notes:
 * - Solution derived from first principles: visits in list order are simply the number of contiguous department blocks.
 * - Optimized visits equal the count of unique departments required.
 * - Time saved = difference. Nothing fancy needed; correctness is straightforward to reason about and to test exhaustively.
 */
public class GroceryDepartmentVisitSaver {

    /**
     * Synthetic prefix used when a product cannot be found in the catalog.
     */
    private static final String UNKNOWN_PREFIX = "__UNKNOWN__:";

    /**
     * Build a lookup map from product name -> department (case-sensitive as per input).
     * If duplicate product names appear, the *first* one wins (can tweak if needed).
     */
    public static Map<String, String> buildCatalogLookup(String[][] products) {
        // We use LinkedHashMap so that the first occurrence is kept (putIfAbsent semantics below preserve order intention).
        Map<String, String> map = new LinkedHashMap<>();
        for (String[] row : products) {
            // Defensive: skip null / malformed rows.
            if (row == null || row.length < 2) continue;
            String product = row[0];
            String dept = row[1];
            // Only record if we do not already have a mapping for this product.
            map.putIfAbsent(product, dept);
        }
        return map;
    }

    /**
     * Lookup department for an item; if missing, return synthetic unique department so it counts as its own visit.
     */
    private static String lookupDept(Map<String, String> catalog, String item) {
        return catalog.getOrDefault(item, UNKNOWN_PREFIX + item);
    }

    /**
     * Count the number of department visits incurred when shopping strictly in the given list order.
     * Example: Depts sequence [Produce, Dairy, Pantry, Dairy] => 4 visits (each switch counts).
     */
    public static int countVisitsInListOrder(Map<String, String> catalog, List<String> shoppingList) {
        String prevDept = null;   // Track department of previous item.
        int visits = 0;           // Count of department runs.
        for (String item : shoppingList) {
            String dept = lookupDept(catalog, item);  // Map item to dept, fallback if unknown.
            // If this is the first item OR its dept differs from previous dept, we are starting a new visit.
            if (prevDept == null || !dept.equals(prevDept)) {
                visits++;
                prevDept = dept; // Update current dept context.
            }
            // Else: same dept as previous; we remain in the same visit; no increment.
        }
        return visits;
    }

    /**
     * Count the minimal number of visits when you group items by department and clear each department fully before moving on.
     * That is just the number of distinct departments among the needed items.
     */
    public static int countVisitsOptimized(Map<String, String> catalog, List<String> shoppingList) {
        // Convert each item to its dept (with unknown fallback), then collect distinct set size.
        return (int) shoppingList.stream()
                .map(item -> lookupDept(catalog, item))
                .distinct() // dedupe
                .count();
    }

    /**
     * Compute time saved = visits(list-order) - visits(optimized).
     */
    public static int timeSaved(Map<String, String> catalog, List<String> shoppingList) {
        int original = countVisitsInListOrder(catalog, shoppingList);
        int optimized = countVisitsOptimized(catalog, shoppingList);
        return original - optimized;
    }

    /* --------------------------------------------------
     * ------------------ TEST HARNESS ------------------
     * -------------------------------------------------- */

    /**
     * Build the sample catalog used in the problem statement.
     */
    private static String[][] sampleCatalog() {
        return new String[][]{
                {"Cheese", "Dairy"},
                {"Carrots", "Produce"},
                {"Potatoes", "Produce"},
                {"Canned Tuna", "Pantry"},
                {"Romaine Lettuce", "Produce"},
                {"Chocolate Milk", "Dairy"},
                {"Flour", "Pantry"},
                {"Iceberg Lettuce", "Produce"},
                {"Coffee", "Pantry"},
                {"Pasta", "Pantry"},
                {"Milk", "Dairy"},
                {"Blueberries", "Produce"},
                {"Pasta Sauce", "Pantry"}
        };
    }

    /**
     * Reference implementation (same logic; spelled out verbosely) used to double-check results in randomized tests.
     * We purposely implement in a slightly different way to catch accidental bugs in the main methods.
     */
    private static int referenceTimeSaved(String[][] catalogArr, List<String> list) {
        Map<String, String> m = Arrays.stream(catalogArr)
                .filter(r -> r != null && r.length >= 2)
                .collect(Collectors.toMap(r -> r[0], r -> r[1], (a, b) -> a)); // first wins
        // visits list order
        int visitsInOrder = 0;
        String prev = null;
        for (String item : list) {
            String d = m.getOrDefault(item, UNKNOWN_PREFIX + item);
            if (prev == null || !prev.equals(d)) {
                visitsInOrder++;
                prev = d;
            }
        }
        // optimized visits
        Set<String> depts = list.stream().map(i -> m.getOrDefault(i, UNKNOWN_PREFIX + i)).collect(Collectors.toSet());
        int visitsOpt = depts.size();
        return visitsInOrder - visitsOpt;
    }

    /**
     * Convenience method to run a single test case and print PASS/FAIL.
     */
    private static void runTest(TestCase tc) {
        Map<String, String> catalog = buildCatalogLookup(tc.catalog);
        int actual = timeSaved(catalog, tc.shoppingList);
        int expected = tc.expectedSaved != null ? tc.expectedSaved : referenceTimeSaved(tc.catalog, tc.shoppingList);
        boolean pass = (actual == expected);
        System.out.printf("[%s] expected=%d actual=%d => %s%n", tc.name, expected, actual, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.println("    Catalog:" + catalog);
            System.out.println("    List:" + tc.shoppingList);
        }
    }

    /**
     * Generate a large random catalog and shopping list for stress testing performance + correctness.
     *
     * @param numDepts number of departments to generate.
     * @param numProds number of products.
     * @param listLen  shopping list length.
     */
    private static TestCase randomCase(String name, int numDepts, int numProds, int listLen) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        // create department names: Dept0, Dept1, ...
        List<String> depts = IntStream.range(0, numDepts)
                .mapToObj(i -> "Dept" + i)
                .collect(Collectors.toList());

        // build catalog rows: Prod0..ProdN -> random dept
        String[][] catalog = new String[numProds][2];
        for (int i = 0; i < numProds; i++) {
            catalog[i][0] = "Prod" + i;
            catalog[i][1] = depts.get(rnd.nextInt(numDepts));
        }

        // build shopping list picking random product names (some may be unknown purposely)
        List<String> list = new ArrayList<>(listLen);
        for (int i = 0; i < listLen; i++) {
            if (rnd.nextDouble() < 0.1) { // 10% unknown items
                list.add("UnknownItem" + rnd.nextInt(numProds));
            } else {
                list.add("Prod" + rnd.nextInt(numProds));
            }
        }

        return new TestCase(name, catalog, list, null); // expected computed by reference
    }

    /**
     * Build the curated set of deterministic test cases.
     */
    private static List<TestCase> buildDeterministicTests() {
        String[][] cat = sampleCatalog();
        List<TestCase> tcs = new ArrayList<>();

        // Problem-statement example
        tcs.add(new TestCase(
                "ProblemExample",
                cat,
                Arrays.asList("Blueberries", "Milk", "Coffee", "Flour", "Cheese", "Carrots"),
                2 // from description: 5 visits original, 3 optimized => save 2
        ));

        // Single item -> no savings
        tcs.add(new TestCase(
                "SingleItem",
                cat,
                Collections.singletonList("Milk"),
                0 // original=1, optimized=1
        ));

        // All same department contiguous -> no savings
        tcs.add(new TestCase(
                "AllSameDeptContiguous",
                cat,
                Arrays.asList("Cheese", "Milk", "Chocolate Milk"),
                0 // visits orig=1 opt=1
        ));

        // Same dept but separated by others -> savings occurs
        tcs.add(new TestCase(
                "BounceBetweenSameDept",
                cat,
                Arrays.asList("Cheese", "Coffee", "Milk", "Pasta", "Chocolate Milk"),
                2 // sequence Dairy->Pantry->Dairy->Pantry->Dairy =5 ; opt(depts=Dairy,Pantry)=2 ; save=3? wait: 5-2=3
        ));

        // NOTE: need to correct expected above (typo). We'll temporarily put null and let reference compute.
        tcs.set(tcs.size() - 1, new TestCase(
                "BounceBetweenSameDept",
                cat,
                Arrays.asList("Cheese", "Coffee", "Milk", "Pasta", "Chocolate Milk"),
                null // use reference to avoid manual slip
        ));

        // Unknown item in list -> treated as its own visit & dept
        tcs.add(new TestCase(
                "UnknownItems",
                cat,
                Arrays.asList("Mystery", "Cheese", "Mystery", "Coffee"),
                null // compute via reference
        ));

        // Empty list -> expect 0
        tcs.add(new TestCase(
                "EmptyList",
                cat,
                Collections.emptyList(),
                0
        ));

        return tcs;
    }

    /**
     * Build a collection of randomized large test cases.
     */
    private static List<TestCase> buildRandomTests() {
        List<TestCase> tcs = new ArrayList<>();
        tcs.add(randomCase("RandomSmall", 5, 50, 100));
        tcs.add(randomCase("RandomMedium", 20, 5_000, 10_000));
        tcs.add(randomCase("RandomLarge", 100, 100_000, 200_000)); // stress; adjust if memory limited
        return tcs;
    }

    /**
     * Entry point: runs deterministic + random tests, prints PASS/FAIL summary.
     */
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();
        tests.addAll(buildDeterministicTests());
        tests.addAll(buildRandomTests());

        long start = System.currentTimeMillis();
        tests.forEach(GroceryDepartmentVisitSaver::runTest);
        long end = System.currentTimeMillis();
        System.out.printf("--- Completed %d tests in %d ms ---%n", tests.size(), (end - start));
    }

    /**
     * Simple container to hold a named test case.
     */
    private static class TestCase {
        final String name;                 // Human-friendly label printed in results.
        final String[][] catalog;          // Master product->dept data.
        final List<String> shoppingList;   // Ordered shopping list.
        final Integer expectedSaved;       // Expected time saved; null means auto-calc via reference.

        TestCase(String name, String[][] catalog, List<String> shoppingList, Integer expectedSaved) {
            this.name = name;
            this.catalog = catalog;
            this.shoppingList = shoppingList;
            this.expectedSaved = expectedSaved;
        }
    }
}
