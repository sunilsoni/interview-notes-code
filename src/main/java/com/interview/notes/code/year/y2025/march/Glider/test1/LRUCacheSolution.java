package com.interview.notes.code.year.y2025.march.Glider.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*


### **LRU Cache Operations**

Design a data structure that follows the constraints of a **Least Recently Used (LRU) cache**.

Your **LRU Cache** should support the following operations:

1. **`LRUCache(int capacity)`**
   - Initialize the LRU cache with a positive **capacity**.
   - No keys should be present in the cache initially.

2. **`int get(int key)`**
   - Return the **value** of the key if it exists.
   - Otherwise, return **-1**.

3. **`void put(int key, int value)`**
   - Update the **value** of the key if the key exists.
   - Otherwise, add the **key-value** pair to the cache.
   - If the number of keys exceeds the **capacity**, evict the **least recently used key**.

**Note:** Any key that is accessed for a valid `get()` or `put()` operation can be considered a recently used key.

---

### **Input Format**
1. The first line of input contains an **integer N**, representing the **capacity** of the cache.
2. The second line of input contains an **integer M**, representing the **number of operations**.
3. The third line of input contains **M** space-separated strings, each representing an operation in one of the following formats:
   - **`GET,x`** → Get the value of key `x` present in the cache.
   - **`PUT,x,y`** → Update the value of key `x` if `x` exists; otherwise, add the **(x, y)** key-value pair to the cache.

---

### **Output**
- An **array** of values returned by the `GET` operations.

---

### **Constraints**
- \(1 \leq N \leq 20\)

---

### **Example 1**
#### **Input**
```
2
6
GET,2 PUT,1,100 PUT,2,125 PUT,3,150 GET,1 GET,3
```
#### **Output**
```
-1 -1 150
```
#### **Explanation**
The operations on the **LRU cache with capacity 2** are:

1. **`GET,2`** → The cache is initially empty. Key `2` does not exist, so return **-1**.
2. **`PUT,1,100`** → Insert key `1` with value `100` → `[(1,100)]`
3. **`PUT,2,125`** → Insert key `2` with value `125` → `[(1,100), (2,125)]`
4. **`PUT,3,150`** → Cache is **full**, so **evict the least recently used key** (`1`) and insert `(3,150)` → `[(2,125), (3,150)]`
5. **`GET,1`** → Key `1` does not exist, so return **-1**.
6. **`GET,3`** → Key `3` exists, return its value **150**.

Final output: **`-1 -1 150`**

---

### **Example 2**
#### **Input**
```
3
5
PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
```
#### **Output**
```
75 50
```
#### **Explanation**
Operations on the **LRU cache with capacity 3**:

1. **`PUT,11,25`** → Insert key `11` with value `25` → `[(11,25)]`
2. **`PUT,22,50`** → Insert key `22` with value `50` → `[(11,25), (22,50)]`
3. **`PUT,11,75`** → Key `11` exists, update its value → `[(22,50), (11,75)]`
4. **`GET,11`** → Key `11` exists, return **75**.
5. **`GET,22`** → Key `22` exists, return **50**.

Final output: **`75 50`**

 */
class LRUCacheSolution {

    public static List<Integer> solve(int capacity, List<String> ar) {
        LRUCache cache = new LRUCache(capacity);
        List<Integer> result = new ArrayList<>();

        ar.forEach(op -> {
            String[] parts = op.split(",");
            if ("GET".equals(parts[0])) {
                result.add(cache.get(Integer.parseInt(parts[1])));
            } else if ("PUT".equals(parts[0])) {
                int key = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                cache.put(key, value);
            }
        });

        return result;
    }

    // Method to verify if expected matches actual
    private static void verify(List<Integer> expected, List<Integer> actual, String testCase) {
        if (expected.equals(actual)) {
            System.out.println(testCase + " PASSED");
        } else {
            System.out.println(testCase + " FAILED. Expected: " + expected + ", but got: " + actual);
        }
    }

    public static void main(String[] args) {

        // Provided Test Case 1
        verify(
                Arrays.asList(-1, -1, 150),
                solve(2, Arrays.asList("GET,2", "PUT,1,100", "PUT,2,125", "PUT,3,150", "GET,1", "GET,3")),
                "Test Case 1"
        );

        // Provided Test Case 2
        verify(
                Arrays.asList(75, 50),
                solve(3, Arrays.asList("PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22")),
                "Test Case 2"
        );

        // Additional edge test cases:

        // Empty cache test
        verify(
                Arrays.asList(-1),
                solve(1, Arrays.asList("GET,10")),
                "Empty Cache Test"
        );

        // Capacity overflow test
        verify(
                Arrays.asList(-1, 200),
                solve(1, Arrays.asList("PUT,5,100", "PUT,10,200", "GET,5", "GET,10")),
                "Capacity Overflow Test"
        );

        // Large input performance test
        int largeCapacity = 10000;
        List<String> largeInput = IntStream.rangeClosed(1, 20000)
                .mapToObj(i -> "PUT," + i + "," + (i * 10))
                .collect(Collectors.toList());
        largeInput.add("GET,1");          // Should be evicted (-1)
        largeInput.add("GET,20000");      // Should be available (200000)

        verify(
                Arrays.asList(-1, 200000),
                solve(largeCapacity, largeInput),
                "Large Input Performance Test"
        );
    }

    private static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private final int capacity;

        LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

        public int get(int key) {
            return getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }
    }
}
