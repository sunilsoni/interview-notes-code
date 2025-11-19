package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.stream.IntStream;

public class SecondHighestOptimized {

    public static String findSecondHighestWithFrequency(int[] arr) {

        if (arr == null || arr.length == 0) return "0-0";

        Integer max = null, second = null;

        for (int n : arr) {
            if (max == null || n > max) {
                second = max;
                max = n;
            } else if (n != max && (second == null || n > second)) {
                second = n;
            }
        }

        if (second == null) return "0-0";

        final int finalSecond = second;

        long count = IntStream.of(arr).filter(x -> x == finalSecond).count();

        return finalSecond + "-" + count;
    }

    public static void main(String[] args) {
        test(null, "0-0");
        test(new int[]{}, "0-0");
        test(new int[]{8,9,8,5,4,8,3,8}, "8-4");
        test(new int[]{10,10,10}, "0-0");
        test(new int[]{5,4,3,2}, "4-1");
        test(new int[]{1,1,1,2,2}, "1-3");
        test(new int[]{-1,-5,-1,-7,-5}, "-5-2");

        int[] large = IntStream.range(0, 1_000_000).map(i -> i % 50).toArray();
        System.out.println(findSecondHighestWithFrequency(large));
    }

    private static void test(int[] arr, String expected) {
        String r = findSecondHighestWithFrequency(arr);
        System.out.println((r.equals(expected) ? "PASS" : "FAIL") +
                " | Expected: " + expected + " | Got: " + r);
    }
}
