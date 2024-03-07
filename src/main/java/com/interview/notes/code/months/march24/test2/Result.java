package com.interview.notes.code.months.march24.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Final:
 * <p>
 * 1. Question 1
 * There is an array of integers, arr, and an integer value X. For each element in another array of integers, query_values, return the 1-based index in arr of the query_values i occurrence of X. If X does not occur query_values[i] times, return -1 for that query.
 * Example
 * arr = [1, 2, 20, 8, 8, 1, 2, 5, 8, 01
 * X = 8
 * query_values = [100, 4, 2]
 * There is no 100th or 4th instance of X = 8 in arr. The 2nd occurrence is at index 5. Return [-1, -1, 5].
 * Function Description
 * Complete the function kthOccurrence in the editor.
 * kthOccurrence has the following parameters):
 * int X: the integer to search for int arr[n] : the elements to search
 * int query_values[q] : the occurrence of X to find the index of
 * Returns
 * int[q]: the index in arr or -1 for each query
 * Constraints
 * • 1 < n, q <=2 * 105
 * • 0 <= arr, X, query_values <= 109
 * <p>
 * <p>
 * • Input Format For Custom Testing
 * <p>
 * The first line contains an integer, the value of X.
 * <p>
 * Next line contains a single integer n, the size of arr.
 * <p>
 * The next n lines each contain an integer, arr[il.
 * <p>
 * The next line contains a single integer q, the size of query_values.
 * <p>
 * The next q lines each contain an integer, query_values].
 * <p>
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 8
 * 10
 * 1
 * 2
 * 20
 * 8
 * 8
 * 1
 * 2
 * 5
 * 8
 * 0
 * 5
 * 100
 * 2
 * 1
 * 3
 * 4
 * arr[] size n = 10
 * arr11 = 11, 2, 20, 8, 8, 1, 2, 5, 8, 01
 * ->
 * query_values] size q = 5
 * query_values [] = [100, 2, 1, 3, 41
 * <p>
 * -1
 * <p>
 * 5
 * <p>
 * 4
 * <p>
 * 9
 * <p>
 * -1
 * <p>
 * Sample Output
 * Explanation
 * There is no 100th or 4th occurrence of 8, hence, for those queries, the answer is -1. For the rest,
 * it is the index of the kth occurrence of X = 8.
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 9
 * 4
 * 9
 * 8
 * 9
 * 9
 * 4
 * 7
 * 3
 * 7
 * 6
 * - X = 9
 * arr[] size n = 4
 * arr11 = 19, 8, 9, 91
 * query_values] size q = 4
 * query_values 11 = 17, 3, 7, 61
 * Sample Output
 * -1
 * 4
 * -1
 * -1
 * Sample Explanation
 * There is no 6th or 7th occurrence of 9. The 3rd occurrence is at index 4.
 */
class Result {

    public static List<Integer> kthOccurrence(int X, List<Integer> arr, List<Integer> query_values) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            indexMap.computeIfAbsent(arr.get(i), k -> new ArrayList<>()).add(i + 1);
        }

        List<Integer> result = new ArrayList<>();
        List<Integer> indices = indexMap.getOrDefault(X, new ArrayList<>());

        for (int target : query_values) {
            result.add(indices.size() < target ? -1 : indices.get(target - 1));
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 20, 8, 8, 1, 2, 5, 8, 0);
        int X = 8;
        List<Integer> query_values = List.of(100, 4, 2);
        System.out.println(kthOccurrence(X, arr, query_values)); // Expected: [-1, -1, 5]

        arr = List.of(9, 8, 9, 9);
        X = 9;
        query_values = List.of(7, 3, 7, 6);
        System.out.println(kthOccurrence(X, arr, query_values)); // Expected: [-1, 4, -1, -1]
    }
}
