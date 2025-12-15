package com.interview.notes.code.year.y2025.december.common.test1;

public class SubarraySum {
    public static void main(String[] args) {
        int[] arr = {8, 10, 15, 7, 6, 2};
        int target = 17;

        boolean found = false;

        for (int start = 0; start < arr.length; start++) {
            int sum = 0;
            for (int end = start; end < arr.length; end++) {
                sum += arr[end];
                if (sum == target) {
                    System.out.println("Subarray found from index " + start + " to " + end);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No subarray with sum " + target);
        }
    }
}
