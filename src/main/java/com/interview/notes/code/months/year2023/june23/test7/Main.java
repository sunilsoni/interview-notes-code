package com.interview.notes.code.months.year2023.june23.test7;

public class Main {
    public static void main(String[] args) {


        int[] array = {1, 2, 4, 1, 2};
        int n = 5;

        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            if ((array[i] > array[i - 1] && array[i] > array[i + 1]) ||
                    (array[i] < array[i - 1] && array[i] < array[i + 1])) {
                count++;
                // Choose the direction that requires removing the middle element
                if (array[i - 1] < array[i + 1]) {
                    array[i + 1] = array[i];
                } else {
                    array[i - 1] = array[i];
                }
            }
        }
        System.out.println(count);
    }
}
