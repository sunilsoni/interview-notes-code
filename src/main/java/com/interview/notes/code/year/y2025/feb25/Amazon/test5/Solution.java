package com.interview.notes.code.year.y2025.feb25.Amazon.test5;

import java.util.List;
import java.util.Arrays;
/*
WORKING 100%

public static long calculateMinCost(String password, String reference,
List<Integer> cost) {
// Write your code here


1. Code Question 1

A team of developers at Amazon is working on a feature that checks the strength of
a password as it is set by a user.

Analysts found that people use their personal information in passwords, which is
insecure. The feature searches for the presence of a reference string as a
subsequence of any permutation of the password. If any permutation contains the
reference string as a subsequence, then the feature determines the minimum cost
to remove characters from password so that no permutation contains the string
reference as a subsequence.

The removal of any character has a cost given in an array, cost, with 26 elements
that represent the cost to replace 'a' (cost{0/) through 'z' (cost[25]). Given two
strings password and reference, determine the minimum total cost to remove
characters from password so that no permutation contains the string reference as
a subsequence.

Note:

e¢ A permutation is a sequence of integers from 1 to n of length n containing each
number exactly once, for example [1, 3, 2] is a permutation while [1, 2, 1] is not.

e Asubsequence is a sequence that can be derived from another sequence by deleting
some or no elements, without changing the order of the remaining elements. For
example, given the string "abcde", the following are subsequences: "a", "ace", "be",
"bde" etc. while sequences like "dea", "acbde" are not subsequences.

Example

Suppose password ="adefgh", reference ="hf", and cost =[1, 0, 0, 2, 4, 4, 3, 1, 0, 0,
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0].

Some possible removals from password so that no permutation contains the string
reference as a subsequence are:


Some possible removals from password so that no permutation contains the string
reference as a subsequence are:

Case 1 Case 2

Cost 1 2. 4 4 3 1 Cost 1 2 4 4 3 1

Character Character

Remove letter 'h’ Remove letter 'f
Cost of removal = 1 Cost of removal = 4

e In Case 1 we are removing only a single letter 'h' so the cost of removal in this case
becomes 1.

e Similarly in Case 2 we are removing only a single letter 'f' so the cost of removal in this
case becomes 4.

Since removing either h or f will remove the existence of reference as a
subsequence so we optimally choose to remove 'h' (Case 1), therefore the
minimum cost is 1.

Function Description
Complete the function ca/culateMinCost in the editor below.

calculateMinCost has the following parameters:

string password: the password string

string reference: the string which should not exist as a subsequence in any
permutation of password

int cost[26]: the costs to remove each character in the lowercase English
alphabet


Returns

long int: the minimum cost to remove characters from password such that no
permutation contains the reference string as a subsequence

Constraints

¢ 1< /password] < 10°
° 1< /reference/ < 10°
© 0< cost[i] < 109 for jin range [0, 25]

e The strings password and reference consist of lowercase English letters only.

Vv Input Format For Custom Testing

The first line contains a string, password.
The next line contains a string, reference.

The next line should contain a constant integer 26, denoting the next 26 lines
describe the array ost.

Each of the next 26 lines contains an integer, cost/i].


v Sample Case 0

Sample Input For Custom Testing

STDIN FUNCTION
abcdcbcb > password = "abcdcbcb"
bcb > reference = "bcb"
26 = constant integer, 26
> cost[26] = [2, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0,

? 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

oo oo ooooo o oo oo ecoooaooaoeooaoaeoaskR FPF WON

Sample Output


Sample Output
3

Explanation
An optimal move is to remove the occurrences of the letter 'c' resulting in a
modified password = "abdbb". The total cost of removal = 1*3 = 3.


v Sample Case 1

Sample Input For Custom Testing

STDIN FUNCTION

kkkk > password = "kkkk"
k = reference = "k"
26 > constant integer, 26
5 > cost[26] = [5, 1, 1, 2, 4, 7, 3, 4, 5, 7, 5, 6,
2, 1, 5, 12, 5, 1, 5, 0, 5, 6, 4, 7, 8, 50]
1

1

2

4

7

3

4

5

7

5

6

2

1

5

12

5

1

5

(0)

5

6

4

7

8

50

Sample Output

20


Sample Output
20

Explanation
Remove 5 occurrences of 'k' at a cost of 5*4 = 20.


 */
public class Solution {

    /**
     * Calculates the minimum cost to remove characters from the password so that
     * no permutation of the remaining characters contains the reference string
     * as a subsequence.
     *
     * The idea is simple:
     * - A permutation can be arranged arbitrarily. Thus, if the multiset of characters
     *   in the password contains each character of reference in at least the same count,
     *   then one can arrange a permutation that has reference as a subsequence.
     * - To break that possibility, it is enough to reduce the count of any one letter (that
     *   appears in reference) below its frequency in reference.
     * - For each letter L in reference:
     *      if passwordCount(L) < referenceCount(L) then the condition is already broken.
     *      Otherwise, we need to remove at least (passwordCount(L) - (referenceCount(L) - 1))
     *      occurrences of L.
     * - Multiply the number of removals by the cost to remove that letter.
     * - The answer is the minimum cost among all letters in reference.
     *
     * Time Complexity: O(n) for counting + O(26) constant work.
     *
     * @param password the original password string
     * @param reference the subsequence that must not be possible
     * @param cost a list of 26 integers representing removal cost for 'a' to 'z'
     * @return the minimum total removal cost
     */
    public static long calculateMinCost(String password, String reference, List<Integer> cost) {
        int[] countPassword = new int[26];
        int[] countReference = new int[26];
        
        // Count frequency of each character in the password.
        for (char c : password.toCharArray()) {
            countPassword[c - 'a']++;
        }
        
        // Count frequency of each character in the reference string.
        for (char c : reference.toCharArray()) {
            countReference[c - 'a']++;
        }
        
        // Check if the password is already safe.
        // If for any character in the reference, the count in the password is less than the required count,
        // then no permutation can form the reference string.
        for (int i = 0; i < 26; i++) {
            if (countReference[i] > 0 && countPassword[i] < countReference[i]) {
                return 0;
            }
        }
        
        // For each character in the reference, compute the removal cost required to make its count less than
        // what is needed to form the reference subsequence.
        long minCost = Long.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (countReference[i] > 0) {
                // Removal needed: (passwordCount - (referenceCount - 1))
                int removalNeeded = countPassword[i] - countReference[i] + 1;
                long candidateCost = (long) removalNeeded * cost.get(i);
                minCost = Math.min(minCost, candidateCost);
            }
        }
        return minCost;
    }
    
    /**
     * Runs a test case with the provided inputs and expected output.
     *
     * @param testName Name/description of the test case.
     * @param password Test input: password string.
     * @param reference Test input: reference string.
     * @param cost Test input: cost array as a List of 26 integers.
     * @param expected Expected minimum cost.
     */
    private static void runTest(String testName, String password, String reference, List<Integer> cost, long expected) {
        long result = calculateMinCost(password, reference, cost);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL. Expected " + expected + " but got " + result);
        }
    }
    
    /**
     * Main method for testing. It includes sample cases provided in the problem statement,
     * additional edge cases, and a large input simulation.
     */
    public static void main(String[] args) {
        // Test Case 1: Example from the description.
        // password = "adefgh", reference = "hf"
        // cost = [1, 0, 0, 2, 4, 4, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        // Expected removal: remove 'h' (cost=1) → Total cost = 1.
        List<Integer> cost1 = Arrays.asList(1, 0, 0, 2, 4, 4, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        runTest("Test Case 1", "adefgh", "hf", cost1, 1);
        
        // Test Case 2: Sample Case 0 from the prompt.
        // password = "abcdcbcb", reference = "bcb"
        // cost = [2, 3, 1, 4, 0, ... 0] for 26 elements.
        // Expected removal: remove three 'c's at cost=1 each → Total cost = 3.
        List<Integer> cost2 = Arrays.asList(2, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        runTest("Test Case 2", "abcdcbcb", "bcb", cost2, 3);
        
        // Test Case 3: Sample Case 1 from the prompt.
        // password = "kkkk", reference = "k"
        // cost for 'k' (index 10) is 5; removal needed = 4 (since count=4 and reference count=1)
        // Expected total cost = 4 * 5 = 20.
        List<Integer> cost3 = Arrays.asList(5, 1, 1, 2, 4, 7, 3, 4, 5, 7, 5, 6, 2, 1, 5, 12, 5, 1, 5, 0, 5, 6, 4, 7, 8, 50);
        runTest("Test Case 3", "kkkk", "k", cost3, 20);
        
        // Test Case 4: Already safe – one character in reference is missing.
        // password = "abc", reference = "abcd" (letter 'd' is missing in the password)
        // Expected total cost = 0.
        List<Integer> cost4 = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        runTest("Test Case 4", "abc", "abcd", cost4, 0);
        
        // Test Case 5: Large data input simulation.
        // Simulate a password with 100,000 'a's and reference "aaa".
        // For letter 'a': count = 100000, reference count = 3, removal needed = 100000 - 3 + 1 = 99998.
        // If cost for 'a' is 1, expected cost = 99998.
        int largeCount = 100000;
        char[] largePassArr = new char[largeCount];
        Arrays.fill(largePassArr, 'a');
        String largePassword = new String(largePassArr);
        String largeReference = "aaa";
        List<Integer> cost5 = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        runTest("Test Case 5", largePassword, largeReference, cost5, 99998L);
    }
}