package com.interview.notes.code.months.march24.test16;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 4. Prefix Hierarchy
 * Given a list of names, determine the number of names in that list for which a given query string is a prefix. The prefix must be at least 1 character less than the entire name string.
 * Example
 * names = ['jackson', 'jacques, 'jack']
 * query = [jack']
 * The complete query string 'jack' is a prefix of jackson but not of jacques or jack. The prefix cannot contain the entire name string, so jack' does not qualify.
 * Function Description
 * Complete the function findCompletePrefixes in the editor below. The function must return an array of integers that each denotes the number of names strings for which a query string is a prefix.
 * findCompletePrefixes has the following parameters):
 * string names[n]: an array of name strings string querylq]: an array of query strings
 * Returns:
 * int[q]: each valueli] is the answer to queryli]
 * Constraints
 * • 1 ≤ n≤ 20000
 * • 2 ≤ length of names[i], query[i] ≤ 30,
 * • 1 ≤ sum of the lengths of all names ≤ 5 × 105
 * • 1 sq≤ 200
 *
 *
 * • Input Format For Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the size of the array names.
 * Each of the next n lines contains an element names where 0 si < n.
 * The next line contains an integer q, the size of the array query.
 * Each of the next q lines contains an element queryli] where 0 si < q.
 * • Sample Case 0
 * Sample Input 0
 * STDIN
 * Function
 * 10
 * steve stevens danny steves dan john johnny joe alex alexander
 * 5
 * steve alex
 * joe john
 * dan
 * query[] size q = 5
 * query = ['steve'
 * , 'alex' , 'joe', 'john', 'dan']
 * Sample Output 0
 * 2
 * 1
 * 0
 * 1
 * 1
 * Explanation 0
 * Query 1: steve appears as a prefix in two strings: stevens and steves.
 * Query 2: alex appears as a prefix in one string: alexander.
 * Query 3: joe does not appear as a prefix in any string.
 * Query 4: john appears as a prefix in one string: johnny.
 * Query 5: dan appears as a prefix in one string: danny.
 * names []
 * sizen = 10
 * names
 * = ['steve', 'stevens', 'danny', 'steves', 'dan', 'john', 'johnny', 'joe', 'alex', 'alexander']
 *
 *
 * import java.io.*;.
 * class Result {
 * /*
 * * Complete the 'findCompletePrefixes' function below.
 * *
 * * The function is expected to return an INTEGER_ARRAY.
 * * The function accepts following parameters:
 * * 1. STRING_ARRAY names
 * * 2. STRING_ARRAY query
 * * /
 * public static List<integer› findComp LetePretixes (List<String)
 *
 *  }
 *  }
 */
//WORKING

class FindCompletePrefixes {

    public static List<Integer> findCompletePrefixes(List<String> names, List<String> query) {
        Map<String, Integer> prefixCount = new TreeMap<>();

        // Build a prefix map for efficient prefix counting
        for (String name : names) {
            for (int i = 1; i < name.length(); i++) {
                String prefix = name.substring(0, i);
                prefixCount.put(prefix, prefixCount.getOrDefault(prefix, 0) + 1);
            }
        }

        List<Integer> results = new ArrayList<>();

        // Find the count for each query string
        for (String q : query) {
            results.add(prefixCount.getOrDefault(q, 0)); // Use 0 if not found
        }

        return results;
    }
    public static List<Integer> findCompletePrefixes1(List<String> names, List<String> query) {
        return query.stream()
                    .map(q -> (int) names.stream()
                    .filter(name -> name.startsWith(q) && name.length() == q.length() + 1)
                    .count())
                    .collect(Collectors.toList());
    }
    public static void main(String[] args) throws IOException {
        List<String> names = Arrays.asList("steve", "stevens", "danny", "steves", "dan", "john", "johnny", "joe", "alex", "alexander");
        List<String> query = Arrays.asList("steve", "alex", "joe", "john", "dan");
        List<Integer> result = FindCompletePrefixes.findCompletePrefixes(names, query);

        for (int res : result) {
            System.out.println(res);
        }
    }
}