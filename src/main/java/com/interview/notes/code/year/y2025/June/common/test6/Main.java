package com.interview.notes.code.year.y2025.June.common.test6;

import java.util.*;
import java.util.stream.*;
/*

### **LRU Cache Operations**

Design a data structure that follows the constraints of a **Least Recently Used (LRU)** cache.

Your **LRU Cache** should support the following operations:

```java
LRUCache(int capacity)
```

* Initialize the cache with a positive capacity.
* No keys should be present in the cache initially.

```java
int get(int key)
```

* Return the value of the key if the key exists.
* Otherwise, return `-1`.

```java
void put(int key, int value)
```

* Update the value of the key if the key exists.
* Otherwise, add the key-value pair to the cache.
* If the number of keys exceeds the capacity, **evict the least recently used** key.

> **Note:** Any key accessed in a valid `get()` or `put()` operation is considered a recently used key.

---

### **Input Format**

* First line: an integer `N`, the capacity of the cache.
* Second line: an integer `M`, the number of operations.
* Third line: `M` space-separated operations of format:

  * `GET,x` → Get the value of key `x`.
  * `PUT,x,y` → Insert or update the key `x` with value `y`.

---

### **Output Format**

* An array of integers representing the return values of the `GET` operations in the order they appear.

---

### **Constraints**

* `1 ≤ N ≤ 20`

---

### **Function Signature**

```java
public static List<Integer> solve(int capacity, List<String> ar)
```

---

### **Example 1**

**Input:**

```
2
6
GET,2 PUT,1,100 PUT,2,125 PUT,3,150 GET,1 GET,3
```

**Output:**

```
-1 -1 150
```

---

### **Example 2**

**Input:**

```
3
5
PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
```

**Output:**

```
75 50
```


 */
public class Main {
    // LRU Cache implementation using LinkedHashMap
    static class LRUCache {
        private final LinkedHashMap<Integer, Integer> map;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > LRUCache.this.capacity;
                }
            };
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    // Parses and processes the operations, returns list of GET results
    public static List<Integer> solve(int capacity, List<String> ops) {
        LRUCache cache = new LRUCache(capacity);

        return ops.stream()
            .map(op -> {
                String[] parts = op.split(",");
                String cmd = parts[0].trim();
                if ("GET".equals(cmd)) {
                    int k = Integer.parseInt(parts[1].trim());
                    return cache.get(k);            // side-effect: marks as recently used
                } else if ("PUT".equals(cmd)) {
                    int k = Integer.parseInt(parts[1].trim());
                    int v = Integer.parseInt(parts[2].trim());
                    cache.put(k, v);
                    return null;                   // no output for PUT
                } else {
                    throw new IllegalArgumentException("Unknown op: " + op);
                }
            })
            .filter(Objects::nonNull)             // keep only GET results
            .collect(Collectors.toList());
    }

    // Simple equality check for lists
    private static boolean equalsList(List<Integer> a, List<Integer> b) {
        return Objects.equals(a, b);
    }

    public static void main(String[] args) {
        // --- Provided examples ---
        List<String> ex1 = Arrays.asList("GET,2", "PUT,1,100", "PUT,2,125", "PUT,3,150", "GET,1", "GET,3");
        List<Integer> out1 = solve(2, ex1);
        List<Integer> exp1 = Arrays.asList(-1, -1, 150);
        System.out.println("Example 1: " + (equalsList(out1, exp1) ? "PASS" : "FAIL"));

        List<String> ex2 = Arrays.asList("PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22");
        List<Integer> out2 = solve(3, ex2);
        List<Integer> exp2 = Arrays.asList(75, 50);
        System.out.println("Example 2: " + (equalsList(out2, exp2) ? "PASS" : "FAIL"));

        // --- Edge cases ---
        // 1) All GET on empty cache
        List<String> ex3 = Arrays.asList("GET,1", "GET,2");
        List<Integer> out3 = solve(1, ex3);
        List<Integer> exp3 = Arrays.asList(-1, -1);
        System.out.println("Edge (GET only): " + (equalsList(out3, exp3) ? "PASS" : "FAIL"));

        // 2) Over-capacity eviction
        List<String> ex4 = Arrays.asList("PUT,1,1", "PUT,2,2", "PUT,3,3", "GET,1", "GET,2", "GET,3");
        List<Integer> out4 = solve(2, ex4);
        List<Integer> exp4 = Arrays.asList(-1, 2, 3);
        System.out.println("Edge (eviction): " + (equalsList(out4, exp4) ? "PASS" : "FAIL"));

        // --- Large-input performance check ---
        int cap = 1000, M = 10_000;
        Random rnd = new Random(0);
        List<String> largeOps = IntStream.range(0, M)
            .mapToObj(i -> {
                if (rnd.nextBoolean()) {
                    int k = rnd.nextInt(2000);
                    int v = rnd.nextInt(1_000_000);
                    return "PUT," + k + "," + v;
                } else {
                    int k = rnd.nextInt(2000);
                    return "GET," + k;
                }
            })
            .collect(Collectors.toList());

        long start = System.nanoTime();
        List<Integer> largeRes = solve(cap, largeOps);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        // We expect it to finish in well under 2000 ms
        System.out.println("Large test (" + M + " ops): "
            + (elapsedMs < 2000 ? "PASS" : "FAIL")
            + " [" + elapsedMs + " ms]");
    }
}