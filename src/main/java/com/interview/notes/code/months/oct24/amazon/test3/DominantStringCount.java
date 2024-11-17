package com.interview.notes.code.months.oct24.amazon.test3;


/*

100% WORKING : /Users/sunilsoni/Documents/Work/Interview/interview-notes-code/src/main/java/com/interview/notes/code/months/nov24/amazon/test21/DominantSubstringCounter.java

PARTIAL WORKING 12/15



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
public class DominantStringCount {

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";  // Expected: 3
        String testCase2 = "aidfg";    // Expected: 4
        String testCase3 = "dfdffdfi"; // Expected: 13

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3));

        // Large input test case (insert your large input string here)
        String testCaseLarge = "your_large_input_string_here";
        System.out.println("Large Test Case Result: " + getDominantStringCount(testCaseLarge));
    }

    public static int getDominantStringCount(String s) {
        int n = s.length();
        int result = 0;

        // Limit the maximum substring length to 500 to ensure efficiency
        int maxLength = Math.min(n, 500);

        // Precompute prefix sums for each character
        int[][] prefixCounts = new int[26][n + 1];

        for (int i = 0; i < n; i++) {
            // Copy counts from the previous position
            for (int c = 0; c < 26; c++) {
                prefixCounts[c][i + 1] = prefixCounts[c][i];
            }
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                int charIndex = ch - 'a';
                prefixCounts[charIndex][i + 1]++;
            } else {
                // If the character is not a lowercase letter, you can choose to handle it
                // For now, we skip incrementing any counts
            }
        }

        // For all even lengths from 2 to maxLength
        for (int L = 2; L <= maxLength; L += 2) {
            // Slide a window of length L
            for (int i = 0; i <= n - L; i++) {
                int j = i + L;
                // For substring s[i..j-1], check if any character occurs exactly L / 2 times
                boolean found = false;
                for (int c = 0; c < 26; c++) {
                    int count = prefixCounts[c][j] - prefixCounts[c][i];
                    if (count == L / 2) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    result++;
                }
            }
        }

        return result;
    }
}
