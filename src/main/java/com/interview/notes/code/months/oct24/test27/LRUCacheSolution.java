package com.interview.notes.code.months.oct24.test27;

import java.util.*;

/*

WORKING



### LRU Cache Operations

Design a data structure that follows the constraints of a **Least Recently Used (LRU)** cache.

Your **LRU Cache** should support the following operations:

1. `LRUCache(int capacity)`: Initialize the LRU cache with a positive capacity.
   - No keys should be present in the cache initially.

2. `int get(int key)`: Return the value of the key if it exists.
   - Otherwise, return `-1`.

3. `void put(int key, int value)`: Update the value of the key if it exists.
   - Otherwise, add the key-value pair to the cache.
   - If the number of keys exceeds the capacity from this operation, evict the least recently used key.

**Note:** Any key that is accessed for a valid `get()` or `put()` operation can be considered a recently used key.

---

**Input**

- The first line of input contains an integer `N`, representing the capacity of the cache.
- The second line of input contains an integer `M`, representing the number of operations.
- The third line of input contains `M` space-separated strings, each representing an operation. The format of an operation can be one of these:
  - `GET, x`: Get the value of key `x` present in the cache.
  - `PUT, x, y`: Update the value of the key `x` if `x` exists; else, add the key-value `(x, y)` pair to the cache.

**Output**

An array of values returned by the `GET` operations.

**Constraints**

- \( 1 \leq N \leq 20 \)

---

### Examples

#### Example #1

- **Input**:
  ```
  2
  6
  GET, 2 PUT, 1, 100 PUT, 2, 125 PUT, 3, 150 GET, 1 GET, 3
  ```

- **Output**:
  ```
  -1 -1 150
  ```

**Explanation**:
So the operations on the LRU cache with capacity 2 are:
1. `GET, 2` – The Cache is initially empty. i.e., Key 2 does not exist, so return `-1`.
2. `PUT, 1, 100` – Insert Key 1 with value `100`.
3. `PUT, 2, 125` – Insert Key 2 with value `125`.
4. `PUT, 3, 150` – Cache is full, so delete the least recently used key 1 and insert the new pair `(2, 125), (3, 150)`.
5. `GET, 1` – Key 1 does not exist, so return `-1`.
6. `GET, 3` – Key 3 exists, so return its value `150`.

The final array will be `[-1, -1, 150]`.

#### Example #2

- **Input**:
  ```
  3
  5
  PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
  ```

- **Output**:
  ```
  75 50
  ```

**Explanation**:
The operations on the LRU cache with capacity 3 are:
1. `PUT,11,25` – Insert Key 11 with value `25`.
2. `PUT,22,50` – Insert Key 22 with value `50`.
3. `PUT,11,75` – Key 11 exists in the cache, so update the Key 11 with value `75`.
4. `GET, 11` – Key 11 exists, so return its value `75`.
5. `GET, 22` – Key 22 exists, so return its value `50`.

The final array will be `[75, 50]`.

---

### Function Signature

```java
/*
 * Implement method/function with name 'solve' below.
 * The function accepts the following as parameters:
 *   1. capacity is of type int.
 *   2. ar is of type List<String>.
 * Returns: List<Integer>.

public static List<Integer> solve(int capacity, List<String> ar){
    // Write your code here

    return; // return type "List<Integer>".
}


 */
class LRUCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

public class LRUCacheSolution {
    public static List<Integer> solve(int capacity, List<String> operations) {
        LRUCache cache = new LRUCache(capacity);
        List<Integer> results = new ArrayList<>();

        for (String operation : operations) {
            String[] parts = operation.split(",");
            String op = parts[0].trim();

            if (op.equals("GET")) {
                int key = Integer.parseInt(parts[1].trim());
                results.add(cache.get(key));
            } else if (op.equals("PUT")) {
                int key = Integer.parseInt(parts[1].trim());
                int value = Integer.parseInt(parts[2].trim());
                cache.put(key, value);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        // Test case 1
        int capacity1 = 2;
        List<String> operations1 = Arrays.asList(
                "GET, 2", "PUT, 1, 100", "PUT, 2, 125", "PUT, 3, 150", "GET, 1", "GET, 3"
        );
        List<Integer> expected1 = Arrays.asList(-1, -1, 150);
        testCase(1, capacity1, operations1, expected1);

        // Test case 2
        int capacity2 = 3;
        List<String> operations2 = Arrays.asList(
                "PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22"
        );
        List<Integer> expected2 = Arrays.asList(75, 50);
        testCase(2, capacity2, operations2, expected2);

        // Additional test case for large data
        int capacity3 = 1000;
        List<String> operations3 = new ArrayList<>();
        List<Integer> expected3 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            operations3.add("PUT," + i + "," + (i * 10));
            if (i % 100 == 0) {
                operations3.add("GET," + i);
                expected3.add(i * 10);
            }
        }
        testCase(3, capacity3, operations3, expected3);
    }

    private static void testCase(int caseNumber, int capacity, List<String> operations, List<Integer> expected) {
        System.out.println("Test Case " + caseNumber + ":");
        List<Integer> result = solve(capacity, operations);
        boolean passed = result.equals(expected);
        System.out.println(passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
        System.out.println();
    }
}
