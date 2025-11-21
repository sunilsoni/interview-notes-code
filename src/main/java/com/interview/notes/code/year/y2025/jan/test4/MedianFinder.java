package com.interview.notes.code.year.y2025.jan.test4;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    private final PriorityQueue<Integer> smallNums; // max heap
    private final PriorityQueue<Integer> largeNums; // min heap

    public MedianFinder() {
        smallNums = new PriorityQueue<>(Collections.reverseOrder());
        largeNums = new PriorityQueue<>();
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Basic odd number of elements
        System.out.println("Test Case 1:");
        MedianFinder mf1 = new MedianFinder();
        mf1.addNum(1);
        System.out.println("Expected: 1.0, Got: " + mf1.findMedian());
        mf1.addNum(2);
        System.out.println("Expected: 1.5, Got: " + mf1.findMedian());
        mf1.addNum(3);
        System.out.println("Expected: 2.0, Got: " + mf1.findMedian());

        // Test Case 2: Large numbers
        System.out.println("\nTest Case 2:");
        MedianFinder mf2 = new MedianFinder();
        mf2.addNum(Integer.MAX_VALUE);
        mf2.addNum(Integer.MIN_VALUE);
        mf2.addNum(0);
        System.out.println("Expected: 0.0, Got: " + mf2.findMedian());

        // Test Case 3: Stream of numbers
        System.out.println("\nTest Case 3:");
        MedianFinder mf3 = new MedianFinder();
        int[] stream = {5, 2, 3, 4, 1};
        for (int num : stream) {
            mf3.addNum(num);
            System.out.println("After adding " + num + ", Median is: " + mf3.findMedian());
        }
    }

    public void addNum(int num) {
        smallNums.offer(num);
        largeNums.offer(smallNums.poll());

        if (smallNums.size() < largeNums.size()) {
            smallNums.offer(largeNums.poll());
        }
    }

    public double findMedian() {
        if (smallNums.size() > largeNums.size()) {
            return smallNums.peek();
        }
        return (smallNums.peek() + largeNums.peek()) / 2.0;
    }
}
