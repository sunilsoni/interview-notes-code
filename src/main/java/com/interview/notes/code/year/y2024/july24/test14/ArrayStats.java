package com.interview.notes.code.year.y2024.july24.test14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayStats {

    public static double getMean(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return (double) sum / nums.length;
    }

    public static int getMode(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        int mode = nums[0];
        for (int n : nums) {
            int count = countMap.getOrDefault(n, 0) + 1;
            countMap.put(n, count);
            if (count > maxCount) {
                maxCount = count;
                mode = n;
            }
        }
        return mode;
    }

    public static double getMedian(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        Arrays.sort(nums);
        int mid = nums.length / 2;
        if (nums.length % 2 == 0) {
            return (nums[mid - 1] + nums[mid]) / 2.0;
        } else {
            return nums[mid];
        }
    }

    public static String getLongestNonRepeatingSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int start = 0, maxLength = 0;
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastSeen[c] >= start) {
                start = lastSeen[c] + 1;
            }
            if (i - start + 1 > maxLength) {
                maxLength = i - start + 1;
                longest = s.substring(start, i + 1);
            }
            lastSeen[c] = i;
        }
        return longest;
    }

    public static String addFractions(String frac1, String frac2) {
        String[] parts1 = frac1.split("/");
        String[] parts2 = frac2.split("/");
        int num1 = Integer.parseInt(parts1[0]);
        int den1 = Integer.parseInt(parts1[1]);
        int num2 = Integer.parseInt(parts2[0]);
        int den2 = Integer.parseInt(parts2[1]);

        int lcm = den1 * den2 / gcd(den1, den2);
        int resultNum = num1 * (lcm / den1) + num2 * (lcm / den2);
        int resultDen = lcm;

        int commonFactor = gcd(resultNum, resultDen);
        resultNum /= commonFactor;
        resultDen /= commonFactor;

        return resultNum + "/" + resultDen;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        // Test getMean
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Mean: " + getMean(numbers));

        // Test getMode
        int[] modeNumbers = {1, 2, 2, 3, 3, 3, 4};
        System.out.println("Mode: " + getMode(modeNumbers));

        // Test getMedian
        int[] medianNumbers = {5, 2, 8, 1, 9};
        System.out.println("Median: " + getMedian(medianNumbers));

        // Test getLongestNonRepeatingSubstring
        String str = "abcabcbb";
        System.out.println("Longest non-repeating substring: " + getLongestNonRepeatingSubstring(str));

        // Test addFractions
        String fraction1 = "1/3";
        String fraction2 = "1/6";
        System.out.println("Sum of fractions: " + addFractions(fraction1, fraction2));
    }
}
