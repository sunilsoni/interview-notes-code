package com.interview.notes.code.year.y2023.july23.test11;

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
public class LatencyAverager1 {

    private final int k;
    private Queue<Integer> latencies;
    private double currentSum;

    public LatencyAverager1(int k) {
        this.k = k;
        this.latencies = new LinkedList<>();
        this.currentSum = 0.0;
    }

    public static void main(String[] args) {
        LatencyAverager1 averager = new LatencyAverager1(5);

        averager.addLatency(50);
        averager.addLatency(60);
        averager.addLatency(70);
        averager.addLatency(50);
        averager.addLatency(100);
        averager.addLatency(10);

        System.out.println("Average: " + averager.getAverage());
        averager.printWindow();

        averager.addLatency(40);
        System.out.println("Average: " + averager.getAverage());
        averager.printWindow();
    }

    // This method is called each time a new latency value is received.
    public void addLatency(int latency) {
        latencies.add(latency);
        currentSum += latency;

        // If the size of the queue exceeds K, we remove the oldest value.
        if (latencies.size() > k) {
            currentSum -= latencies.poll();
        }
    }

    // This method returns the rolling average of the last K latencies.
    public double getAverage() {
        if (latencies.size() == 0) return 0;
        return currentSum / latencies.size();
    }

    // Utility function to print the current window
    public void printWindow() {
        System.out.println(latencies);
    }
}
