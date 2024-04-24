package com.interview.notes.code.months.april24.test13;

import java.util.*;

class Outcome5 {
    public static List<Integer> missingAndDouble(List<Integer> A) {
        int n = A.size();
        int expectedSum = n * (n + 1) / 2; // Sum of numbers from 1 to n
        int actualSum = 0;
        int repeatedSum = 0;

        for (int num : A) {
            actualSum += num;
            repeatedSum += Math.abs(num) - 1; // XOR operation to find repeated number
        }

        int missingNum = expectedSum - actualSum + repeatedSum;
        int repeatedNum = repeatedSum - missingNum;

        List<Integer> result = new ArrayList<>();
        result.add(Math.abs(repeatedNum));
        result.add(missingNum);

        return result;
    }


    public static void main(String[] args) {
        List<Integer> sample1 = Arrays.asList(3, 2, 3);
        List<Integer> sample2 = Arrays.asList(4, 6, 7, 3, 5, 4, 7, 8);

        List<Integer> result1 = missingAndDouble(sample1);
        List<Integer> result2 = missingAndDouble(sample2);

        System.out.println("Sample 1:");
        System.out.println("Repeated: " + result1.get(0) + ", Missing: " + result1.get(1));
        System.out.println("Sample 2:");
        System.out.println("Repeated: " + result2.get(0) + ", Missing: " + result2.get(1));
    }
}