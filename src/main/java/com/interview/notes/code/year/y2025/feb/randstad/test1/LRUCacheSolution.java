package com.interview.notes.code.year.y2025.feb.randstad.test1;

import java.util.*;

/*
WORKING 90%


## LRU Cache Operations

Design a data structure that follows the constraints of a **Least Recently Used (LRU)** cache.

Your **LRU Cache** should support the following operations:

- **`LRUCache(int capacity)`**: Initialize the LRU cache with a positive capacity. No keys should be present in the cache initially.
- **`int get(int key)`**: Return the value of the key if it exists. Otherwise, return `-1`.
- **`void put(int key, int value)`**: Update the value of the key if the key exists. Otherwise, add the key-value `(x, y)` pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

### **Note**:
Any key that is accessed for a valid `get()` OR `put()` operation can be considered as a recently used key.

---

### **Input Format**
- The first line of input contains an integer **N**, representing the capacity of the cache.
- The second line of input contains an integer **M**, representing the number of operations.
- The third line of input contains **M space-separated strings**, each representing an operation.
  - **GET, x**: Get the value of key **x** present in the cache.
  - **PUT, x, y**: Update the value of key **x** if **x** exists, else add the key-value `(x, y)` pair to the cache.

### **Output Format**
An array of values returned by the `GET` operations.

### **Constraints**
`1 ≤ N ≤ 20`

---

## **Example #1**
### **Input**
```
2
6
GET,2 PUT,1,100 PUT,2,125 PUT,3,150 GET,1 GET,3
```

### **Output**
```
-1 -1 150
```

### **Explanation**
The operations on LRU cache with capacity `2` are:

1. **GET, 2** → The Cache is initially empty. Key `2` does not exist, so return `-1`.
2. **PUT,1,100** → Insert Key `1` with value `100` → `[ (1,100) ]`
3. **PUT,2,125** → Insert Key `2` with value `125` → `[ (1,100), (2,125) ]`
4. **PUT,3,150** → Cache is full, so delete the least recently used key `1` and insert the new pair → `[ (2,125), (3,150) ]`
5. **GET, 1** → Key `1` does not exist, so return `-1`
6. **GET, 3** → Key `3` exists, so return its value `150`

**Final Output:** `[-1, -1, 150]`

---

## **Example #2**
### **Input**
```
3
5
PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
```

### **Output**
```
75 50
```

### **Explanation**
The operations on LRU cache with capacity `3` are:

1. **PUT,11,25** → Insert Key `11` with value `25` → `[ (11,25) ]`
2. **PUT,22,50** → Insert Key `22` with value `50` → `[ (11,25), (22,50) ]`
3. **PUT,11,75** → Key `11` exists in the cache, so update Key `11` with value `75` → `[ (22,50), (11,75) ]`
   *(Here, LRU will be key `22` since `11` is updated with a new value.)*
4. **GET, 11** → Key `11` exists, so return `75`
5. **GET, 22** → Key `22` exists, so return `50`

**Final Output:** `[75, 50]`

---

## **Function Signature**
```java
/*
* Implement method/function with name 'solve' below.
* The function accepts the following as parameters:
* 1. N is of type int.
* 2. ar is of type List<String>.
* return List<Integer>.

public static List<Integer> solve(int N, List<String> ar) {
    // Write your code here
    return;
}
```

        ---

This is the cleaned and structured version of the extracted text from the images.

 */
public class LRUCacheSolution {

    public static List<Integer> solve(int capacity, List<String> operations) {
        LRUCache cache = new LRUCache(capacity);
        List<Integer> result = new ArrayList<>();
        for (String op : operations) {
            String[] parts = op.split(",");
            String command = parts[0].trim();
            if (command.equals("GET")) {
                int key = Integer.parseInt(parts[1].trim());
                result.add(cache.get(key));
            } else if (command.equals("PUT")) {
                int key = Integer.parseInt(parts[1].trim());
                int value = Integer.parseInt(parts[2].trim());
                cache.put(key, value);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        testExample1();
        testExample2();
        testEdgeCase();
        testLargeData();
    }

    private static void testExample1() {
        int capacity = 2;
        List<String> ops = Arrays.asList(
                "GET,2",
                "PUT,1,100",
                "PUT,2,125",
                "PUT,3,150",
                "GET,1",
                "GET,3"
        );
        List<Integer> expected = Arrays.asList(-1, -1, 150);
        List<Integer> result = solve(capacity, ops);
        if (result.equals(expected)) {
            System.out.println("Test Example1: PASS");
        } else {
            System.out.println("Test Example1: FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    private static void testExample2() {
        int capacity = 3;
        List<String> ops = Arrays.asList(
                "PUT,11,25",
                "PUT,22,50",
                "PUT,11,75",
                "GET,11",
                "GET,22"
        );
        List<Integer> expected = Arrays.asList(75, 50);
        List<Integer> result = solve(capacity, ops);
        if (result.equals(expected)) {
            System.out.println("Test Example2: PASS");
        } else {
            System.out.println("Test Example2: FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    private static void testEdgeCase() {
        int capacity = 1;
        List<String> ops = Arrays.asList(
                "PUT,1,10",
                "PUT,2,20",
                "GET,1",
                "GET,2"
        );
        List<Integer> expected = Arrays.asList(-1, 20);
        List<Integer> result = solve(capacity, ops);
        if (result.equals(expected)) {
            System.out.println("Test EdgeCase: PASS");
        } else {
            System.out.println("Test EdgeCase: FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    private static void testLargeData() {
        int capacity = 3;
        List<String> ops = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        // Each cycle (8 operations) produces outputs: [10, -1, 30, 10]
        int cycles = 125; // 8 * 125 = 1000 operations
        for (int i = 0; i < cycles; i++) {
            ops.add("PUT,1,10");
            ops.add("PUT,2,20");
            ops.add("PUT,3,30");
            ops.add("GET,1");
            expected.add(10);
            ops.add("PUT,4,40");
            ops.add("GET,2");
            expected.add(-1);
            ops.add("GET,3");
            expected.add(30);
            ops.add("GET,1");
            expected.add(10);
        }
        List<Integer> result = solve(capacity, ops);
        if (result.equals(expected)) {
            System.out.println("Test LargeData: PASS");
        } else {
            System.out.println("Test LargeData: FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    static class LRUCache {
        private final int capacity;
        private final LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
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
}