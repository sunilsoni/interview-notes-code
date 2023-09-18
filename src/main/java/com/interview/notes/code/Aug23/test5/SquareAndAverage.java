package com.interview.notes.code.Aug23.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//code logic: From the array exclude the square values greater than 100 and provide average of the sum of remaining values int[] ={15,6,10,9,30,8,2}.
public class SquareAndAverage {
    public static void main(String[] args) {
        int[] numbers = {15, 6, 10, 9, 30, 8, 2};

        // Filter out square values greater than 100
        int[] filteredNumbers = filterSquares(numbers);

        // Calculate the average of the remaining values
        double average = calculateAverage(filteredNumbers);

        System.out.println("Filtered Numbers: " + Arrays.toString(filteredNumbers));
        System.out.println("Average of Filtered Numbers: " + average);
    }

    // Function to filter out square values greater than 100
    public static int[] filterSquares(int[] numbers) {
        List<Integer> filteredList = new ArrayList<>();
        for (int number : numbers) {
            int square = number * number;
            if (square <= 100) {
                filteredList.add(number);
            }
        }
        return filteredList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Function to calculate the average of an array of integers
    public static double calculateAverage(int[] numbers) {
        if (numbers.length == 0) {
            return 0.0; // Handle the case of an empty array
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.length;
    }
}
