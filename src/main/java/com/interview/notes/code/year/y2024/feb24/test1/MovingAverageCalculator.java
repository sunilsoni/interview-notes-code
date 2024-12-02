package com.interview.notes.code.year.y2024.feb24.test1;

import java.util.Arrays;

public class MovingAverageCalculator {

    public static double[] computeMovingAverage(int[] input, int windowSize) {
        int n = input.length;
        double[] result = new double[n - windowSize + 1];

        for (int i = 0; i <= n - windowSize; i++) {
            int sum = 0;
            for (int j = i; j < i + windowSize; j++) {
                sum += input[j];
            }
            result[i] = (double) sum / windowSize;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        int windowSize = 3;

        double[] movingAverages = computeMovingAverage(input, windowSize);
        System.out.println("Moving Averages: " + Arrays.toString(movingAverages));
    }
}
