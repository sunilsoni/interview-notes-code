package com.interview.notes.code.months.sept24.test16;

import java.util.ArrayList;
import java.util.List;

public class RollingWindowAverage {

    // Method to calculate rolling window averages
    public static List<Double> getRollingAverages(int[] array, int k) {
        List<Double> result = new ArrayList<>();
        
        // Edge case: if the array length is less than k, return an empty list
        if (array.length < k) {
            return result;
        }
        
        // Calculate the sum of the first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += array[i];
        }
        
        // Add the average of the first window to the result
        result.add((double) windowSum / k);
        
        // Slide the window across the array
        for (int i = k; i < array.length; i++) {
            // Update the window sum by subtracting the element that is left behind
            // and adding the new element
            windowSum = windowSum - array[i - k] + array[i];
            
            // Add the new average to the result
            result.add((double) windowSum / k);
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test case
        int[] input = {1, 2, 3, 4, 5};
        int windowSize = 3;
        
        // Get the rolling averages
        List<Double> averages = getRollingAverages(input, windowSize);
        
        // Output the result
        System.out.println(averages);  // Expected output: [2.0, 3.0, 4.0]
    }
}
