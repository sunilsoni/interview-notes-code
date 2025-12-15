package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.Collections;
import java.util.PriorityQueue;

public class StreamingTemperatureStats {
    // For median approximation, we’ll use two heaps (works well in practice)
    private final PriorityQueue<Double> lowerHalf = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private final PriorityQueue<Double> upperHalf = new PriorityQueue<>(); // min-heap
    private long count = 0;          // number of readings
    private double sum = 0.0;        // cumulative sum
    private double maxTemp = Double.NEGATIVE_INFINITY;

    // Demo
    public static void main(String[] args) {
        StreamingTemperatureStats stats = new StreamingTemperatureStats();
        double[] readings = {30.5, 32.0, 31.2, 29.8, 33.1, 30.0};

        for (double r : readings) {
            stats.processTemperature(r);
        }

        System.out.println("Max Temp: " + stats.getMax());
        System.out.println("Mean Temp: " + stats.getMean());
        System.out.println("Median Temp: " + stats.getMedian());
    }

    // Process each new temperature reading
    public void processTemperature(double temp) {
        count++;
        sum += temp;
        maxTemp = Math.max(maxTemp, temp);

        // Maintain heaps for median
        if (lowerHalf.isEmpty() || temp <= lowerHalf.peek()) {
            lowerHalf.offer(temp);
        } else {
            upperHalf.offer(temp);
        }

        // Balance heaps
        if (lowerHalf.size() > upperHalf.size() + 1) {
            upperHalf.offer(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    public double getMean() {
        return (count == 0) ? 0.0 : sum / count;
    }

    public double getMax() {
        return (count == 0) ? Double.NaN : maxTemp;
    }

    public double getMedian() {
        if (count == 0) return Double.NaN;
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else {
            return lowerHalf.peek();
        }
    }
}
