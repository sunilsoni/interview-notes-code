package com.interview.notes.code.year.y2025.June.apple.test3;

/*

### **Problem Statement: Longest Common Prefix**

Write a function that takes in a collection of strings and returns the string that is their **longest common prefix**.

#### **Example:**

Given the input:

```java
["apple", "ape", "apricot"]
```

The function should return:

```
"ap"
```

---

### **Starter Code in Java:**

```java
class Solution {

    public static String longestCommonPrefix(String[] strings) {
        // TODO
    }

    public static void main(String[] args) {
        String[] words = {"apple", "ape", "apricot"};
        System.out.println("Longest Common Prefix: " + longestCommonPrefix(words));
    }
}
```

 */
public class Solution {

    public static String longestCommonPrefix(String[] strings) {
        // Handle edge cases - if array is null or empty
        if (strings == null || strings.length == 0) {
            return "";
        }

        // If array has only one string, return that string
        if (strings.length == 1) {
            return strings[0];
        }

        // Take first string as initial prefix
        String prefix = strings[0];

        // Iterate through remaining strings
        for (int i = 1; i < strings.length; i++) {
            // While current string doesn't start with prefix
            while (!strings[i].startsWith(prefix)) {
                // Reduce prefix length by 1 from end
                prefix = prefix.substring(0, prefix.length() - 1);
                // If prefix becomes empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        test(new String[]{"apple", "ape", "apricot"}, "ap");

        // Test Case 2: Empty array
        test(new String[]{}, "");

        // Test Case 3: Single string
        test(new String[]{"hello"}, "hello");

        // Test Case 4: No common prefix
        test(new String[]{"dog", "cat", "bird"}, "");

        // Test Case 5: All same strings
        test(new String[]{"test", "test", "test"}, "test");

        // Test Case 6: Large strings
        test(new String[]{"verylongstring", "verylongprefix", "verylong"}, "verylong");

        // Test Case 7: Null input
        test(null, "");
    }

    // Helper method to run tests
    private static void test(String[] input, String expectedOutput) {
        String result = longestCommonPrefix(input);
        String inputStr = input == null ? "null" : String.join(", ", input);

        System.out.println("Input: [" + inputStr + "]");
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Result: " + result);
        System.out.println("Test " + (result.equals(expectedOutput) ? "PASSED" : "FAILED"));
        System.out.println("------------------------");
    }
}
