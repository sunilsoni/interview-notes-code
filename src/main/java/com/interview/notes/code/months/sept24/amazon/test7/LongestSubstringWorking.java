package com.interview.notes.code.months.sept24.amazon.test7;

/*


/*
* Complete the 'getLongestSubstring' function below.
* The function is expected to return an INTEGER.
* The function accepts STRING s as parameter.
* /
public static int getLongestsubstring(Strings s){
// Write your code here
}



1. Code Question 1
A product name at Amazon is represented as a string s of length n that consists of lowercase English letters.
A team at Amazon working on a product's search algorithm.
They want to know the length of the longest substring in a product name with its first character lexicographically smaller than its last. A valid substring must be longer than 1 character. If no such substring exists, return 0.
Note: A character a is lexicographically smaller than character b if a appears in the English alphabet sequence before b.
Example
The string 5 = "ecbdca".
s[2:4]
e
b
d
C
There are two longest valid substrings:
"cbd", s[1:3] shown, and "bdc".
', s[2:4].
Both start with a smaller character than they end with and have three characters."
s[1:3]
Return their length, 3. Note that "cbdc" is not valid since the starting character is not smaller than the last.
Function Description
nazo
Complete the function getLongestSubstring in the editor below.
getLongestSubstring has the following parameter:
string s: the product name
erRank Con
Returns
int. the length of the longest valid substring

Constraints
amazon
• 25n≤ 105
• s consists of lowercase English letters only.
Confidentiall
165-14-7861

100
• Input Format For Custom Testing
The only line contains a string s.
77960-0609
hook +690
Hacke Sank
• Sample Case 0
Sample Input For Custom Testing
STDIN
FUNCTION
ink Confidential
abcd
s = abcd
Sample Output


4
Explanation
The entire string is a valid substring. Here, a is lexicographically smaller than d, so it is a valid substring.
• Sample Case 1
Pank Con
740/051
Sample Input For Custom Testing
STDIN
- - - - -
†ghbbadcba
FUNCTION
- - - - - - - -
s = fghbbadcba
ok+69c71
Sample Output
5
Explanation
The longest valid substring is "bbadc". Here, b is lexicographically smaller than c, so it is a valid substring.

 */
public class LongestSubstringWorking {

    public static void main(String[] args) {
        // Sample test cases
        System.out.println(getLongestSubstring("abcd"));      // Output: 4
        System.out.println(getLongestSubstring("fghbbadcba")); // Output: 5
    }


    /*
     * Complete the 'getLongestSubstring' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    public static int getLongestSubstring(String s) {
        int n = s.length();

        // Arrays to store the first and last occurrence of each character
        int[] minIndex = new int[26]; // For 'a' to 'z'
        int[] maxIndex = new int[26];

        // Initialize minIndex to n and maxIndex to -1
        for (int i = 0; i < 26; i++) {
            minIndex[i] = n;
            maxIndex[i] = -1;
        }

        // Build minIndex and maxIndex arrays
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a'; // Convert character to index 0-25
            if (minIndex[c] > i) {
                minIndex[c] = i; // Update first occurrence
            }
            maxIndex[c] = i; // Update last occurrence
        }

        int maxLength = 0;

        // Iterate over all pairs of characters c1 and c2 where c1 < c2
        for (int c1 = 0; c1 < 26; c1++) {
            if (minIndex[c1] == n) {
                continue; // Character c1 not present in the string
            }
            for (int c2 = c1 + 1; c2 < 26; c2++) {
                if (maxIndex[c2] == -1) {
                    continue; // Character c2 not present in the string
                }
                if (minIndex[c1] < maxIndex[c2]) {
                    int length = maxIndex[c2] - minIndex[c1] + 1; // Calculate substring length
                    if (length > maxLength) {
                        maxLength = length; // Update maximum length
                    }
                }
            }
        }

        // Return the maximum length if it's at least 2, else return 0
        return maxLength >= 2 ? maxLength : 0;
    }
}
