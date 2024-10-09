package com.interview.notes.code.months.oct24.amz.test1;

import java.util.*;

/*
WORKING

A product name at Amazon is represented as a string s of length n that consists of lowercase English letters.
A team at Amazon working on a product's search algorithm.
They want to know the length of the longest substring in a product name with its first character lexicographically smaller than its last. A valid substring must be longer than 1 character. If no such substring exists, return 0.
Note: A character a is lexicographically smaller than character b if a appears in the English alphabet sequence before b.
amazon no
Example
The string s = "ecdca".
s|2:4]
e
C
b
d
C
a
There are two longest valid substrings:
"cbd", s[1:3] shown, and "bd", s[2:47.
Both start with a smaller character than they end with and have three characters."
infidential
all
$[1:3]
956212d@hook.com
Return their length, 3. Note that "cbdc" is not valid since the starting character is not smaller than the last.
 Function Description
Complete the function getLongestSubstring in the editor below.
getLongestSubstring has the following parameter:
string s: the product name
Dhook.com
lential
Returns
int. the length of the longest valid substring
451d-b61a
Constraints

• 25 n≤ 105
• s consists of lowercase English letters only.
com
idential
• Input Format For Custom Testing
The only line contains a string s.
erRank C
• Sample Case 0
Sample Input For Custom Testing
STDIN
FUNCTIONI

abcd
S
abcd
nfidential!
Sample Output
HackerRank
d-661a-2f83
4

Explanation

The entire string is a valid substring. Here, a is lexicographically smaller than d, so it is a valid substring.

 • Sample Case 1
Sample Input For Custom Testing
STDIN
fghbbadcba
Sample Output
5
FUNCTION
5 = fghbbadcba
erRank. C
amazo
Explanation
The longest valid substring is "bbadc". Here, b is lexicographically smaller than c, so it is a valid substring.

 */
public class GetLongestSubstring {

    /**
     * Finds the length of the longest substring in the given string where the first character is
     * lexicographically smaller than the last character. If no such substring exists, returns 0.
     *
     * @param s the input string
     * @return the length of the longest valid substring
     */
    public static int getLongestSubstring(String s) {
        int n = s.length();
        int[] earliest = new int[26];
        int[] latest = new int[26];
        Arrays.fill(earliest, n);   // Initialize with n (maximum possible index + 1)
        Arrays.fill(latest, -1);    // Initialize with -1 (minimum possible index - 1)

        // Record earliest and latest occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            earliest[c] = Math.min(earliest[c], i);
            latest[c] = Math.max(latest[c], i);
        }

        int maxLen = 0;

        // Iterate over all pairs of characters where c1 < c2
        for (int c1 = 0; c1 < 26; c1++) {
            if (earliest[c1] == n) continue;  // Character c1 does not exist in the string
            for (int c2 = c1 + 1; c2 < 26; c2++) {
                if (latest[c2] == -1) continue;  // Character c2 does not exist in the string
                if (latest[c2] > earliest[c1]) {
                    int len = latest[c2] - earliest[c1] + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }

        // Return the result if valid substring length is greater than 1
        return maxLen >= 2 ? maxLen : 0;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("abcd", 4));
        testCases.add(new TestCase("fghbbadcba", 5));
        testCases.add(new TestCase("aaaa", 0));
        testCases.add(new TestCase("abcba", 4));
        testCases.add(new TestCase("zxy", 2));
        testCases.add(new TestCase("edcba", 0));
        testCases.add(new TestCase("a", 0));
        testCases.add(new TestCase("ab", 2));
        testCases.add(new TestCase("ba", 0));
        testCases.add(new TestCase("abcdecba", 7));

        // Run test cases
        int testCaseNumber = 1;
        for (TestCase testCase : testCases) {
            int result = getLongestSubstring(testCase.input);
            if (result == testCase.expectedOutput) {
                System.out.println("Test case " + testCaseNumber + ": PASS");
            } else {
                System.out.println("Test case " + testCaseNumber + ": FAIL");
                System.out.println("Input: " + testCase.input);
                System.out.println("Expected Output: " + testCase.expectedOutput);
                System.out.println("Actual Output: " + result);
            }
            testCaseNumber++;
        }
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        String input;
        int expectedOutput;

        TestCase(String input, int expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
