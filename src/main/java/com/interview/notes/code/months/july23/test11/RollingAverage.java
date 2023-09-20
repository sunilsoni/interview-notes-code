package com.interview.notes.code.months.july23.test11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * /♦Create a program that measures server latency periodically.
 * The program receives one latency value at a time. On request the program can provide the current rolling average of up to the latest K latency values.
 * Example values:                                                                                                                                J
 * 50, 60, 70, 50, 100, 10, ...more values to come later
 * K = 5
 * Th|“ values in window (i.e. latest K’ values) should be (60, 70, 50, 100, 10)
 * The next value 40 comes in. The window is now (70, 50, 100, 10, 40).
 */
public class RollingAverage {

    private final int k;
    private final Queue<Integer> window;
    private int sum;

    public RollingAverage(int k) {
        this.k = k;
        this.window = new LinkedList<>();
        this.sum = 0;
    }

    public static void main(String[] args) {
        RollingAverage rollingAverage = new RollingAverage(5);
        rollingAverage.add(50);
        rollingAverage.add(60);
        rollingAverage.add(70);
        rollingAverage.add(50);
        rollingAverage.add(100);
        System.out.println("The current rolling average is: " + rollingAverage.getAverage());
        rollingAverage.add(10);
        System.out.println("The current rolling average is: " + rollingAverage.getAverage());
        rollingAverage.add(40);
        System.out.println("The current rolling average is: " + rollingAverage.getAverage());

        /*
        The current rolling average is: 66.0
The current rolling average is: 58.0
The current rolling average is: 54.0
         */
    }

    public void add(int latency) {
        sum += latency;
        window.add(latency);
        if (window.size() > k) {
            sum -= window.remove();
        }
    }

    public double getAverage() {
        if (window.isEmpty()) {
            return 0.0;
        }
        return (double) sum / window.size();
    }
}
