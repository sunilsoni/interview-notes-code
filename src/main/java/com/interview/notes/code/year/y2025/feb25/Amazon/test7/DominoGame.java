package com.interview.notes.code.year.y2025.feb25.Amazon.test7;

import java.util.*;
/*
WORKING 100%


/*

* Complete the 'getMaxPoints!' function below.

*

* The function is expected to return an INTEGER.
x The function accepts following parameters:

* 1. INTEGER_ARRAY domino

* 2. INTEGER_ARRAY remove

* 3. INTEGER min_order

x/

public static int getMaxPoints(List<Integer> domino, List<Integer> remove,
int min_order) {

// Write your code here

}


2. Code Question 2

Amazon Games has recently launched a new game. The game consists of

n dominoes where the i” one has some size dominof[i] and has an order which is
defined as the longest strictly increasing subsequence of the sizes of the dominoes.
An associated array, remove, contains the n integers from 0 to n - 7. The player
performs a move such that in the /” move, the player can remove the domino
numbered remove/i].

Given the arrays domino and remove, find the maximum number of moves that
can be made such that the order of remaining dominoes is at least equal to a given
integer min_order.

Example

n=6

domino = [1, 4, 4, 2, 5, 3]

remove = [2, 1, 4, 0, 5, 3]

min_order = 3

The highlighted cells represent the longest increasing subsequence:


The highlighted cells represent the longest increasing subsequence:
move? mop somnol oa hWN 2b
woes me somnol SAIN 2 BX

On the fourth move, the order becomes 2. Hence the answer is 3.

Function Description

Complete the function getMaxPoints in the editor below.

getMaxPoints takes the following arguments:

int dominof[n]: the sizes of the dominoes
int remove[n]: the dominoes to remove
int min order: the minimum order of dominoes after removals


Returns
int: the maximum number of dominoes that can be removed
Constraints
e 1<n<10°
e 1 <dominofi] < 109
e 0 <remove[i]<n-1
© 1<min_order<n
Vv Input Format For Custom Testing
The first line contains an integer, n, the number of elements in domino.
Each of the next n lines contains an integer, domino/i].
The next line contains an integer, n, the number of elements in remove.
Each of the next n lines contains an integer, remove/i].
The next line contains an integer, min_order.
v Sample Case 0
Sample Input For Custom Testing
STDIN FUNCTION
4 = domino[] size n = 4
1 - domino = [1, 2, 3, 4]
2
3
4
4 - remove[] size n = 4
3 > remove = [3, 2, 1, 0]
2
1
(0)
2 = min_order = 2
Sample Output
Otter Voice I
2


Sample Output
2
Explanation
After two removals, the dominoes are [1, 2]. Any removal now will decrease the
order to 1.
v Sample Case 1
Sample Input For Custom Testing
STDIN FUNCTION
5 = domino[] size n= 5
4 = domino = [4, 5, 58, 5, 4]
5
58
5
4
5 > remove[] size n= 5
1 = remove = [1, 0, 2, 3, 4]
10}
2
3
4
1 > min_order = 1
Sample Output
4
Explanation
Since the minimum order is 1, even after the first four removals, a single element
will suffice.


 */
public class DominoGame {

    // Returns the maximum number of moves (removals) such that the order (LIS) 
    // of the remaining dominoes is at least min_order.
    public static int getMaxPoints(List<Integer> domino, List<Integer> remove, int min_order) {
        int n = domino.size();
        // Binary search for the maximum m (number of removals) that is valid.
        int low = 0, high = n, answer = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(domino, remove, mid, min_order)) {
                answer = mid;  // mid removals are valid; try for more removals.
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }
    
    // Checks if after removing the first m dominoes (by indices in remove list),
    // the longest increasing subsequence (LIS) of the remaining dominoes is at least min_order.
    private static boolean isValid(List<Integer> domino, List<Integer> remove, int m, int min_order) {
        int n = domino.size();
        boolean[] removed = new boolean[n];
        // Mark the first m removals.
        for (int i = 0; i < m; i++) {
            removed[remove.get(i)] = true;
        }
        // Build the remaining domino sequence.
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
                remaining.add(domino.get(i));
            }
        }
        // Compute the length of the longest strictly increasing subsequence.
        int lis = longestIncreasingSubsequence(remaining);
        return lis >= min_order;
    }
    
    // Standard O(n log n) algorithm to compute the length of the longest strictly increasing subsequence.
    private static int longestIncreasingSubsequence(List<Integer> seq) {
        List<Integer> tail = new ArrayList<>();
        for (int num : seq) {
            // Find the insertion point for num in tail using binary search.
            int idx = Collections.binarySearch(tail, num);
            if (idx < 0) {
                idx = -idx - 1;
            }
            if (idx == tail.size()) {
                tail.add(num);
            } else {
                tail.set(idx, num);
            }
        }
        return tail.size();
    }
    
    // Main method to run tests. No JUnit is used; each test prints PASS/FAIL.
    public static void main(String[] args) {
        // Sample Test Case 0:
        // domino = [1, 2, 3, 4], remove = [3, 2, 1, 0], min_order = 2, expected answer = 2.
        List<Integer> domino1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> remove1 = Arrays.asList(3, 2, 1, 0);
        int min_order1 = 2;
        int result1 = getMaxPoints(domino1, remove1, min_order1);
        System.out.println("Test Case 1: Expected: 2, Got: " + result1 + (result1 == 2 ? " PASS" : " FAIL"));
        
        // Sample Test Case 1:
        // domino = [4, 5, 58, 5, 4], remove = [1, 0, 2, 3, 4], min_order = 1, expected answer = 4.
        List<Integer> domino2 = Arrays.asList(4, 5, 58, 5, 4);
        List<Integer> remove2 = Arrays.asList(1, 0, 2, 3, 4);
        int min_order2 = 1;
        int result2 = getMaxPoints(domino2, remove2, min_order2);
        System.out.println("Test Case 2: Expected: 4, Got: " + result2 + (result2 == 4 ? " PASS" : " FAIL"));
        
        // Sample Test Case 2:
        // domino = [1, 4, 4, 2, 5, 3], remove = [2, 1, 4, 0, 5, 3], min_order = 3, expected answer = 3.
        List<Integer> domino3 = Arrays.asList(1, 4, 4, 2, 5, 3);
        List<Integer> remove3 = Arrays.asList(2, 1, 4, 0, 5, 3);
        int min_order3 = 3;
        int result3 = getMaxPoints(domino3, remove3, min_order3);
        System.out.println("Test Case 3: Expected: 3, Got: " + result3 + (result3 == 3 ? " PASS" : " FAIL"));
        
        // Additional Edge Case:
        // A decreasing sequence where the original LIS is 1.
        List<Integer> domino4 = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> remove4 = Arrays.asList(0, 1, 2, 3, 4);
        int min_order4 = 2; // With a decreasing sequence, any removal still gives an LIS of 1.
        int result4 = getMaxPoints(domino4, remove4, min_order4);
        System.out.println("Test Case 4: Expected: 0, Got: " + result4 + (result4 == 0 ? " PASS" : " FAIL"));
        
        // Large Data Test Case:
        // Generating a large input to test efficiency.
        int largeN = 100000;
        List<Integer> dominoLarge = new ArrayList<>();
        List<Integer> removeLarge = new ArrayList<>();
        Random rand = new Random(42);
        for (int i = 0; i < largeN; i++) {
            dominoLarge.add(rand.nextInt(1000000) + 1);
            removeLarge.add(i);
        }
        // Setting a moderate min_order value to ensure validity.
        int min_orderLarge = 500;
        long startTime = System.currentTimeMillis();
        int resultLarge = getMaxPoints(dominoLarge, removeLarge, min_orderLarge);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Test Case: Result: " + resultLarge + " (Time: " + (endTime - startTime) + " ms)");
        // (No expected result here; this test ensures performance on large input.)
    }
}