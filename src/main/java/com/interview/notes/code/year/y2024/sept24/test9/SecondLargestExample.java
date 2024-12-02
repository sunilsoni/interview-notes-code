package com.interview.notes.code.year.y2024.sept24.test9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class SecondLargestExample {
    public static Optional<Integer> findSecondLargest(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 4, 5, 2, 5};
        Optional<Integer> secondLargest = findSecondLargest(nums);

        if (secondLargest.isPresent()) {
            System.out.println("Second largest element: " + secondLargest.get());
        } else {
            System.out.println("Array doesn't have a second largest element.");
        }
    }
}
