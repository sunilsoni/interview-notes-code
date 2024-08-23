package com.interview.notes.code.months.aug24.test22;

public class ThirdLargestElement {
    public static int findThirdLargest(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Array should have at least 3 elements");
        }

        Integer first = null;
        Integer second = null;
        Integer third = null;

        for (int num : nums) {
            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (second == null || num > second) {
                if (num != first) {
                    third = second;
                    second = num;
                }
            } else if (third == null || num > third) {
                if (num != second) {
                    third = num;
                }
            }
        }

        if (third == null) {
            throw new IllegalArgumentException("There is no third largest element");
        }

        return third;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 15, 20, 8, 25, 18};
        System.out.println("Third largest element: " + findThirdLargest(arr));
    }
}
