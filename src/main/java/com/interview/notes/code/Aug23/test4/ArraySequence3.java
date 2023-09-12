package com.interview.notes.code.Aug23.test4;

public class ArraySequence3 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 22, 23, 5, 0, 0, -100, 100};
        
        StringBuilder currentSequence = new StringBuilder();
        StringBuilder maxSequence = new StringBuilder();
        
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxSequence = currentSequence;
                }
                currentSum = 0;
                currentSequence = new StringBuilder();
            } else {
                currentSum += arr[i];
                currentSequence.append(arr[i]).append(",");
            }
        }

        // Check if the last sequence has the max sum (in case the array doesn't end with 0)
        if (currentSum > maxSum) {
            maxSequence = currentSequence;
        }

        if (maxSequence.length() > 0) {
            System.out.println(maxSequence.toString().substring(0, maxSequence.length() - 1));  // remove the last comma
        } else {
            System.out.println("No sequence found!");
        }
    }
}
