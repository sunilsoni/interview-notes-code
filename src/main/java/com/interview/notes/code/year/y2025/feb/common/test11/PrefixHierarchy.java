package com.interview.notes.code.year.y2025.feb.common.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*

### **4. Prefix Hierarchy**

Given a list of names, determine the number of names in that list for which a given query string is a prefix. The prefix must be at least **1 character less** than the entire name string.

#### **Example**
```plaintext
names = ['jackson', 'jacques', 'jack']
query = ['jack']
```
The complete query string **'jack'** is a prefix of **'jackson'** but not of **'jacques'** or **'jack'**.
The prefix **cannot** contain the entire name string, so **'jack'** does not qualify.

---

### **Function Description**
Complete the function **`findCompletePrefixes`** in the editor below.
The function must return an **array of integers** that denotes the number of **names** strings for which a **query** string is a prefix.

#### **Function Signature**
```java
public static List<Integer> findCompletePrefixes(List<String> names, List<String> query) {
    // Write your code here
}
```

#### **Parameters**
- **`string names[n]`**: an array of name strings
- **`string query[q]`**: an array of query strings

#### **Returns**
- **`int[q]`**: Each **value[i]** is the answer to **query[i]**

---

### **Constraints**
- \( 1 \leq n \leq 20000 \)
- \( 2 \leq \) **length of** `names[i]`, `query[i]` \( \leq 30 \)
- \( 1 \leq \) **sum of the lengths of all** `names[i]` \( \leq 5 \times 10^5 \)
- \( 1 \leq q \leq 200 \)

---

### **Input Format for Custom Testing**
- The first line contains an integer **`n`**, the size of the array **`names`**.
- Each of the next **`n`** lines contains an element **`names[i]`** where \( 0 \leq i < n \).
- The next line contains an integer **`q`**, the size of the array **`query`**.
- Each of the next **`q`** lines contains an element **`query[i]`** where \( 0 \leq i < q \).

---

### **Sample Case 0**
#### **Sample Input 0**
```plaintext
10
steve
stevens
danny
steves
dan
john
johnny
joe
alex
alexander
5
steve
alex
joe
john
dan
```

#### **Sample Output 0**
```plaintext
2
1
0
1
1
```

---

### **Explanation 0**
1. **Query:** `"steve"` appears as a prefix in **two** strings: `"stevens"` and `"steves"`. **(Output: 2)**
2. **Query:** `"alex"` appears as a prefix in **one** string: `"alexander"`. **(Output: 1)**
3. **Query:** `"joe"` does **not** appear as a prefix in any string. **(Output: 0)**
4. **Query:** `"john"` appears as a prefix in **one** string: `"johnny"`. **(Output: 1)**
5. **Query:** `"dan"` appears as a prefix in **one** string: `"danny"`. **(Output: 1)**

---



 */
public class PrefixHierarchy {

    /**
     * Finds the number of names for which each query string is a complete prefix
     * A complete prefix must be at least 1 character less than the entire name
     *
     * @param names List of name strings to search in
     * @param query List of query strings to check as prefixes
     * @return List of integers where each value represents the count for the corresponding query
     */
    public static List<Integer> findCompletePrefixes(List<String> names, List<String> query) {
        return query.stream()
                .map(q -> countPrefixMatches(names, q))
                .collect(Collectors.toList());
    }

    /**
     * Counts how many names have the given query as a prefix
     *
     * @param names List of names to check
     * @param query The prefix to search for
     * @return Count of names that have query as a prefix
     */
    private static int countPrefixMatches(List<String> names, String query) {
        return (int) names.stream()
                .filter(name -> isPrefixMatch(name, query))
                .count();
    }

    /**
     * Checks if a query is a valid prefix of a name
     * The prefix must be shorter than the name and the name must start with the prefix
     *
     * @param name  The name to check
     * @param query The potential prefix
     * @return True if query is a valid prefix of name
     */
    private static boolean isPrefixMatch(String name, String query) {
        return name.length() > query.length() && name.startsWith(query);
    }

    public static void main(String[] args) {
        // Test Case 1: Sample case from the problem
        List<String> names1 = Arrays.asList(
                "steve", "stevens", "danny", "steves", "dan",
                "john", "johnny", "joe", "alex", "alexander");
        List<String> query1 = Arrays.asList("steve", "alex", "joe", "john", "dan");
        List<Integer> expected1 = Arrays.asList(2, 1, 0, 1, 1);

        testCase(names1, query1, expected1, "Sample Test Case");

        // Test Case 2: Edge case with empty names list
        List<String> names2 = Collections.emptyList();
        List<String> query2 = Arrays.asList("test", "example");
        List<Integer> expected2 = Arrays.asList(0, 0);

        testCase(names2, query2, expected2, "Empty Names List");

        // Test Case 3: Edge case with empty query list
        List<String> names3 = Arrays.asList("test", "testing", "tester");
        List<String> query3 = Collections.emptyList();
        List<Integer> expected3 = Collections.emptyList();

        testCase(names3, query3, expected3, "Empty Query List");

        // Test Case 4: Case with duplicate names
        List<String> names4 = Arrays.asList("test", "testing", "test", "tester");
        List<String> query4 = List.of("test");
        List<Integer> expected4 = List.of(2);

        testCase(names4, query4, expected4, "Duplicate Names");

        // Test Case 5: Case with longer queries
        List<String> names5 = Arrays.asList("programming", "programmer", "program");
        List<String> query5 = Arrays.asList("program", "programming", "prog");
        List<Integer> expected5 = Arrays.asList(2, 0, 3);

        testCase(names5, query5, expected5, "Longer Queries");

        // Test Case 6: Large input test
        testLargeInput();
    }

    /**
     * Helper method to test and validate a test case
     */
    private static void testCase(List<String> names, List<String> query,
                                 List<Integer> expected, String testName) {
        System.out.println("Running test: " + testName);

        long startTime = System.nanoTime();
        List<Integer> result = findCompletePrefixes(names, query);
        long endTime = System.nanoTime();

        boolean passed = result.equals(expected);

        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("--------------------------");
    }

    /**
     * Tests the solution with a large dataset
     */
    private static void testLargeInput() {
        System.out.println("Running Large Input Test");

        // Generate large list of names (20,000 names)
        List<String> largeNames = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            largeNames.add("name" + i);
        }

        // Generate queries
        List<String> largeQueries = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            largeQueries.add("name" + (i * 100));
        }

        long startTime = System.nanoTime();
        List<Integer> result = findCompletePrefixes(largeNames, largeQueries);
        long endTime = System.nanoTime();

        System.out.println("Large test completed");
        System.out.println("Number of names: " + largeNames.size());
        System.out.println("Number of queries: " + largeQueries.size());
        System.out.println("First 5 results: " + result.subList(0, Math.min(5, result.size())));
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("--------------------------");
    }
}