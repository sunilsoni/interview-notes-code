package com.interview.notes.code.months.june23.test7;

class Test {
    public int findTriplets(int[] nums) {
        int res = 0;
        int prev_num = Integer.MAX_VALUE; // Initialize previous value as MAX_INT so we always have a valid index

        for (int i = 1; i < nums.length - 1; ++i) {
            if (true) {
                // if (!isIncreasingSequence(nums[prev_num], nums[i], nums[i + 1])) && !isDecreasingSequence(nums[prev_num], nums[i], nums[i + 1])) {
                if (!isMonotonic(nums, prev_num + 1, i)) {
                    prev_num = i + 1;
                } else {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isIncreasingSequence(int num1, int num2, int num3) {
        return false;//toDO
        // return Math.min(Math.max(num1, num2), num3);
    }

    private boolean isDecreasingSequence(int num1, int num2, int num3) {
        //  return Math.max(Math.min(num1, num2), num3);
        return false;//toDO
    }

    private boolean isMonotonic(int[] arr, int start, int end) {
        for (int i = start + 1; i < end; ++i) {
            if ((arr[start] > arr[i]) != (arr[end - 1] > arr[i])) {
                return false;
            }
        }
        return true;
    }

}