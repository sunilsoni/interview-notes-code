package com.interview.notes.code.year.y2024.nov24.CodeSignal.test1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        testSolution(new String[]{"+4", "+5", "+6", "+4", "+3", "-4"}, 1, new int[]{0, 0, 2, 1, 4, 0});
        testSolution(new String[]{}, 5, new int[]{});
        testSolution(new String[]{"+100", "+101", "+102", "-100"}, 1, new int[]{0, 0, 1, 0});
        testSolution(new String[]{"+1", "+2", "+3"}, 1, new int[]{0, 0, 1});
        testSolution(new String[]{"-1", "-2", "-3"}, 1, new int[]{0, 0, 0});
    }

    private static void testSolution(String[] queries, int diff, int[] expectedOutput) {
        int[] result = solution(queries, diff);
        System.out.println(Arrays.equals(result, expectedOutput) ?
                "Test passed with output: " + Arrays.toString(result) :
                "Test failed with output: " + Arrays.toString(result));
    }

    public static int[] solution(String[] queries, int diff) {
        Map<Integer, Integer> numberCounts = new HashMap<>();
        List<Integer> results = new ArrayList<>();

        for (String query : queries) {
            int num = Integer.parseInt(query.substring(1));
            if (query.charAt(0) == '+') {
                updateNumberCount(numberCounts, num);
            } else {
                removeNumberCount(numberCounts, num);
            }
            results.add(countTriples(numberCounts, diff));
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void updateNumberCount(Map<Integer, Integer> numberCounts, int num) {
        numberCounts.put(num, numberCounts.getOrDefault(num, 0) + 1);
    }

    private static void removeNumberCount(Map<Integer, Integer> numberCounts, int num) {
        if (numberCounts.containsKey(num)) {
            int count = numberCounts.get(num) - 1;
            if (count == 0) {
                numberCounts.remove(num);
            } else {
                numberCounts.put(num, count);
            }
        }
    }

    private static int countTriples(Map<Integer, Integer> numberCounts, int diff) {
        int count = 0;
        for (int y : numberCounts.keySet()) {
            int x = y + diff;
            int z = y - diff;

            if (numberCounts.containsKey(x) && numberCounts.containsKey(z)) {
                int countY = numberCounts.get(y);
                int countX = numberCounts.get(x);
                int countZ = numberCounts.get(z);

                count += countY * countX * countZ;
            }
        }

        return count;
    }
}