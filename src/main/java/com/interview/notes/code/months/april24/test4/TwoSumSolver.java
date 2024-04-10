package com.interview.notes.code.months.april24.test4;

import java.util.HashMap;
import java.util.Map;

public class TwoSumSolver {

    // Result class to handle outcomes and error messages
    public static class Result {
        public final int[] indices;
        public final String errorMessage;

        private Result(int[] indices, String errorMessage) {
            this.indices = indices;
            this.errorMessage = errorMessage;
        }

        public static Result success(int[] indices) {
            return new Result(indices, null);
        }

        public static Result error(String errorMessage) {
            return new Result(null, errorMessage);
        }

        public boolean isSuccess() {
            return errorMessage == null;
        }
    }

    // The findTwoSum method now returns a Result object
    public Result findTwoSum(int[] nums, int target) {
        if (nums == null) {
            return Result.error("Array must not be null.");
        }
        if (nums.length < 2) {
            return Result.error("Array must contain at least two numbers.");
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexMap.containsKey(complement)) {
                return Result.success(new int[]{indexMap.get(complement), i});
            }
            indexMap.put(nums[i], i);
        }

        return Result.error("No two numbers add up to the target.");
    }

    public static void main(String[] args) {
        TwoSumSolver solver = new TwoSumSolver();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Result result = solver.findTwoSum(nums, target);

        if (result.isSuccess()) {
            System.out.println("Indices found: [" + result.indices[0] + ", " + result.indices[1] + "]");
        } else {
            System.err.println("Error: " + result.errorMessage);
        }
    }
}
