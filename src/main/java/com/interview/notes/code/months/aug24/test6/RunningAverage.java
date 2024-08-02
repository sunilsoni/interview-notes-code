package com.interview.notes.code.months.aug24.test6;

import java.util.Deque;
import java.util.LinkedList;

public class RunningAverage {
    private final int windowSize;
    private final Deque<Double> window;
    private double sum;

    public RunningAverage(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("Window size must be positive");
        }
        this.windowSize = windowSize;
        this.window = new LinkedList<>();
        this.sum = 0.0;
    }

    public static void main(String[] args) {
        // Test case 1: Basic functionality
        RunningAverage ra1 = new RunningAverage(3);
        System.out.println(ra1.next(1)); // Expected: 1.0
        System.out.println(ra1.next(2)); // Expected: 1.5
        System.out.println(ra1.next(3)); // Expected: 2.0
        System.out.println(ra1.next(4)); // Expected: 3.0
        System.out.println(ra1.next(5)); // Expected: 4.0

        // Test case 2: Window size of 1
        RunningAverage ra2 = new RunningAverage(1);
        System.out.println(ra2.next(1)); // Expected: 1.0
        System.out.println(ra2.next(2)); // Expected: 2.0
        System.out.println(ra2.next(3)); // Expected: 3.0

        // Test case 3: Large numbers
        RunningAverage ra3 = new RunningAverage(2);
        System.out.println(ra3.next(1000000)); // Expected: 1000000.0
        System.out.println(ra3.next(2000000)); // Expected: 1500000.0
        System.out.println(ra3.next(3000000)); // Expected: 2500000.0

        // Test case 4: Negative numbers
        RunningAverage ra4 = new RunningAverage(3);
        System.out.println(ra4.next(-1)); // Expected: -1.0
        System.out.println(ra4.next(-2)); // Expected: -1.5
        System.out.println(ra4.next(-3)); // Expected: -2.0
        System.out.println(ra4.next(-4)); // Expected: -3.0

        // Test case 5: Mixed positive and negative numbers
        RunningAverage ra5 = new RunningAverage(4);
        System.out.println(ra5.next(-1)); // Expected: -1.0
        System.out.println(ra5.next(2));  // Expected: 0.5
        System.out.println(ra5.next(-3)); // Expected: -0.6666666666666666
        System.out.println(ra5.next(4));  // Expected: 0.5
        System.out.println(ra5.next(-5)); // Expected: -0.5

        // Test case 6: Floating point numbers
        RunningAverage ra6 = new RunningAverage(3);
        System.out.println(ra6.next(1.5)); // Expected: 1.5
        System.out.println(ra6.next(2.7)); // Expected: 2.1
        System.out.println(ra6.next(3.2)); // Expected: 2.4666666666666666
        System.out.println(ra6.next(4.1)); // Expected: 3.3333333333333335
    }

    public double next(double val) {
        if (window.size() == windowSize) {
            sum -= window.removeFirst();
        }
        window.addLast(val);
        sum += val;
        return sum / window.size();
    }
}
