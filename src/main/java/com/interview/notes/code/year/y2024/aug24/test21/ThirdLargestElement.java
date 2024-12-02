package com.interview.notes.code.year.y2024.aug24.test21;

import java.util.Arrays;
import java.util.Comparator;

public class ThirdLargestElement {
    public static int findThirdLargest(int[] nums) {
        return Arrays.stream(nums).boxed().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No third largest element"));
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 15, 20, 8, 25, 18};
        System.out.println("Third largest element: " + findThirdLargest(arr));
    }
}
