package com.interview.notes.code.months.june24.test5;

public class Test {
    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {3, 4}};
        int sum = 0;
        for (int[] subArr : arr) {
            for (int num : subArr) {
                sum += num;
            }
        }
        System.out.println("Sum: " + sum);
    }
}
