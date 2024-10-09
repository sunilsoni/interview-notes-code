package com.interview.notes.code.months.oct24.amz.test2;

import java.util.*;

/*

PARTIAL WORKING 11/15

### **Question:**
A team of data analysts at Amazon is working to identify patterns in data. During their analysis, they found a category of string they call a **dominant string**. A dominant string has the following properties:
1. It has an **even length**.
2. The string contains **at least one character** having a frequency matching exactly **half the length** of the string.

Given a string s of length n, determine the number of its distinct substrings that are dominant strings.

---

### **Example:**
Let’s consider the string s = "dfdffdfi".

All the even-length substrings of s with a frequency of any character that is exactly half their length are:

| Substring | Length of Substring | Frequent Character | Frequency of Character |
|-----------|---------------------|--------------------|------------------------|
| "id"      | 2                   | 'd', 'i'           | 1                      |
| "df"      | 2                   | 'd', 'f'           | 1                      |
| "fi"      | 2                   | 'f', 'i'           | 1                      |
| "df"      | 2                   | 'd', 'f'           | 1                      |
| "fd"      | 2                   | 'f', 'd'           | 1                      |
| "dd"      | 2                   | 'd'                | 2                      |
| "dfdf"    | 4                   | 'd'                | 2                      |
| "fddf"    | 4                   | 'd'                | 2                      |
| "dfdf"    | 4                   | 'd'                | 2                      |
| "fdfd"    | 4                   | 'f', 'd'           | 2                      |
| "dfdf"    | 4                   | 'd'                | 2                      |
| "fdff"    | 4                   | 'f'                | 2                      |
| "idfdfd"  | 6                   | 'd'                | 3                      |
| "dfdfdf"  | 6                   | 'd'                | 3                      |

There are **13 dominant substrings** in the string s = "dfdffdfi".

---

### **Function Description:**
Complete the function getDominantStringCount in the editor below.

getDominantStringCount has the following parameter:
- string s: the string to analyze

Returns:
- **int**: the number of dominant substrings in s

---

### **Input Format:**
The first and only line of input contains a string s.

### **Constraints:**
- \( 1 \leq n \leq 10^5 \)
- s consists of lowercase English letters in the range ['a' to 'z'].

---

### **Examples:**

#### **Sample Input 0:**
s = "aaaaaid"


#### **Sample Output 0:**
3


#### **Explanation 0:**
The following are the dominant substrings:
- 'ai'
- 'id'
- 'aaid'

---

#### **Sample Input 1:**
s = "aidfg"


#### **Sample Output 1:**
4


#### **Explanation 1:**
The following are the dominant substrings:
- 'ai'
- 'id'
- 'df'
- 'fg'

---

### **Additional Example:**
#### **Sample Input 2:**
s = "dfdffdfi"


#### **Sample Output 2:**
13


#### **Explanation 2:**
Refer to the detailed table provided in the example section to see how the dominant substrings are calculated.

---

### **Key Observations for Solution:**
1. We need to consider only **even-length substrings**.
2. For each even-length substring, check if **any character** appears with a frequency equal to **half the length** of the substring.
3. Efficient processing is important since the string length can be as large as \(10^5\).

 */
public class Main {

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";  // Expected: 3
        String testCase2 = "aidfg";    // Expected: 4
        String testCase3 = "dfdffdfi"; // Expected: 13

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3));
    }

    public static int getDominantStringCount(String s) {
        int n = s.length();
        int result = 0;
        int maxLength = Math.min(n, 100); // Limit the maximum length to 100

        // For all even lengths from 2 to maxLength
        for (int L = 2; L <= maxLength; L += 2) {
            int[] counts = new int[26]; // Counts of characters in current window
            // Initialize counts for the first window
            for (int i = 0; i < L; i++) {
                counts[s.charAt(i) - 'a']++;
            }
            // Slide the window
            for (int i = 0; i <= n - L; i++) {
                // Check if any character has frequency L / 2
                for (int c = 0; c < 26; c++) {
                    if (counts[c] == L / 2) {
                        result++;
                        break;
                    }
                }
                // Move the window forward
                if (i + L < n) {
                    counts[s.charAt(i) - 'a']--;
                    counts[s.charAt(i + L) - 'a']++;
                } else if (i + L == n) {
                    // Reached the end of the string
                    break;
                } else {
                    // Do nothing
                }
            }
        }
        return result;
    }
}
