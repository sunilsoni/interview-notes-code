package com.interview.notes.code.year.y2025.march.common.test19;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*

### **Custom Merge**

#### **Programming Challenge Description**
Write a program that, given two sorted arrays of integers, will produce a sorted array containing all elements of both.

There's a special requirement:
> **Sorting must consider all even numbers to be greater than odd numbers.**

Note that the size of the array can be large. Try to find an efficient solution with **linear running time**.

---

### **Input**
- Two comma-separated arrays of integers.
- The two arrays are separated by a semicolon.

**Example:**
```
1,5,7,4;3,7,2
```

---

### **Output**
- A merged and sorted array formatted as a comma-separated series of values.
- The sorting must treat all **even numbers as greater than odd numbers**.

**Example Output:**
```
1,3,5,7,7,2,4
```

---

### **Test 1**

**Test Input:**
```
1,5,7,4;3,7,2
```

**Expected Output:**
```
1,3,5,7,7,2,4
```

---

### **Test 2**

**Test Input:**
```
-17,1,5,11,19,-16,-14,-6,0,4,16,20;-13,-7,-7,-18,-4,-2,4,12
```

**Expected Output:**
```
-17,-13,-7,-7,1,5,11,19,-18,-16,-14,-6,-4,-2,0,4,4,12,16,20
```

 */
public class CustomMerge {

    public static String customMerge(String input) {
        String[] arrays = input.split(";");
        List<Integer> list1 = Arrays.stream(arrays[0].split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> list2 = Arrays.stream(arrays[1].split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Merge and separate odd and even numbers
        List<Integer> mergedOdds = Stream.concat(list1.stream(), list2.stream())
                .filter(n -> n % 2 != 0)
                .sorted()
                .collect(Collectors.toList());

        List<Integer> mergedEvens = Stream.concat(list1.stream(), list2.stream())
                .filter(n -> n % 2 == 0)
                .sorted()
                .collect(Collectors.toList());

        // Combine odd numbers followed by even numbers
        List<Integer> result = Stream.concat(mergedOdds.stream(), mergedEvens.stream())
                .collect(Collectors.toList());

        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    // Minimal reproducible example & Testing in main (No JUnit)
    public static void main(String[] args) {
        test("1,5,7,4;3,7,2", "1,3,5,7,7,2,4");
        test("-17,1,5,11,19,-16,-14,-6,0,4,16,20;-13,-7,-7,-18,-4,-2,4,12",
                "-17,-13,-7,-7,1,5,11,19,-18,-16,-14,-6,-4,-2,0,4,4,12,16,20");

        // Large data test case
        largeDataTest();
    }

    private static void test(String input, String expected) {
        String result = customMerge(input);
        boolean pass = result.equals(expected);
        System.out.println("Test " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    private static void largeDataTest() {
        int size = 1_000_000;
        String odds = IntStream.range(1, size).filter(i -> i % 2 != 0).mapToObj(String::valueOf).collect(Collectors.joining(","));
        String evens = IntStream.range(1, size).filter(i -> i % 2 == 0).mapToObj(String::valueOf).collect(Collectors.joining(","));

        long start = System.currentTimeMillis();
        customMerge(odds + ";" + evens);
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test (1 million elements): " + (end - start) + " ms");
    }
}
