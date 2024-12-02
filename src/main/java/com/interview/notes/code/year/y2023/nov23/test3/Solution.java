package com.interview.notes.code.year.y2023.nov23.test3;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // Example use case
        String[] queries = {"+4", "+5", "+6", "+4", "+3", "-4"};
        int diff = 1;
        int[] results = solution(queries, diff);

        // Output the results
        for (int result : results) {
            System.out.print(result + " ");
        }
    }

    public static int[] solution(String[] queries, int diff) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[queries.length];
        int idx = 0;

        for (String query : queries) {
            char op = query.charAt(0);
            int number = Integer.parseInt(query.substring(1));

            if (op == '+') {
                map.put(number, map.getOrDefault(number, 0) + 1);
            } else {
                map.put(number, map.getOrDefault(number, 0) - 1);
                if (map.get(number) == 0) {
                    map.remove(number);
                }
            }

            int count = 0;
            for (Integer key : map.keySet()) {
                int c1 = map.getOrDefault(key, 0);
                int c2 = map.getOrDefault(key + diff, 0);
                int c3 = map.getOrDefault(key + 2 * diff, 0);
                count += c1 * c2 * c3;
            }
            result[idx++] = count;
        }

        return result;
    }
}
