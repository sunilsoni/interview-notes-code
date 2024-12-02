package com.interview.notes.code.year.y2023.july23.test11;

import java.util.PriorityQueue;

/**
 * Any server may have latency spikes so we want the average latency to be tolerant by only measuring 95th percentile of values.
 * In other words, we ignore the top X values (where X is ~5% of K) in the average.
 * Example values:
 * K = 40
 * i= 2
 * [The values are (60, 70,..., 50, 100, 10), the largest 2 values are 100 and 70.
 * Another example is (70, 70, ..., 50, 100, 10), the largest 2 values are still 100 and 70, note there is another 70 that should be included in the average.
 */
public class LatencyAverager {

    private final int k;
    private final int i;
    private double currentSum;
    private PriorityQueue<Integer> allLatencies;
    private PriorityQueue<Integer> topLatencies;  // for storing top 'i' values

    public LatencyAverager(int k) {
        this.k = k;
        this.i = (int) Math.ceil(0.05 * k);
        this.currentSum = 0.0;
        this.allLatencies = new PriorityQueue<>();
        this.topLatencies = new PriorityQueue<>((a, b) -> b - a);  // max-heap
    }

    public static void main(String[] args) {
        LatencyAverager averager = new LatencyAverager(40);

        // Test values, adjust accordingly
        for (int j = 60; j < 100; j++) {
            averager.addLatency(j);
        }

        averager.addLatency(100);
        averager.addLatency(10);

        System.out.println("Average (95th percentile): " + averager.getAverage());
        averager.printWindow();
    }

    public void addLatency(int latency) {
        allLatencies.add(latency);
        topLatencies.add(latency);
        currentSum += latency;

        if (allLatencies.size() > k) {
            currentSum -= allLatencies.poll();
        }

        // Ensure 'topLatencies' has only top 'i' values
        while (topLatencies.size() > i) {
            topLatencies.poll();
        }
    }

    public double getAverage() {
        if (allLatencies.size() == 0) return 0;

        double sumWithoutTop = currentSum;
        for (int value : topLatencies) {
            sumWithoutTop -= value;
        }

        return sumWithoutTop / (allLatencies.size() - i);
    }

    public void printWindow() {
        System.out.println(allLatencies);
    }
}
