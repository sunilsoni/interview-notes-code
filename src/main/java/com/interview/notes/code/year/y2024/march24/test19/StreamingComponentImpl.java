package com.interview.notes.code.year.y2024.march24.test19;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class StreamingComponentImpl implements StreamingComponent {
    private static final long FIVE_MINUTES_MS = 5 * 60 * 1000;
    private final Queue<DataPoint> dataPoints = new LinkedList<>();
    private final TreeMap<Double, Integer> numberFrequency = new TreeMap<>();
    private double totalSum = 0;

    @Override
    public void load(long timestamp, double number) {
        // Add new data point
        DataPoint newDataPoint = new DataPoint(timestamp, number);
        dataPoints.add(newDataPoint);
        numberFrequency.put(number, numberFrequency.getOrDefault(number, 0) + 1);
        totalSum += number;

        // Remove old data points outside the 5-minute window
        while (!dataPoints.isEmpty() && timestamp - dataPoints.peek().timestamp > FIVE_MINUTES_MS) {
            DataPoint oldDataPoint = dataPoints.poll();
            int count = numberFrequency.get(oldDataPoint.number);
            if (count == 1) {
                numberFrequency.remove(oldDataPoint.number);
            } else {
                numberFrequency.put(oldDataPoint.number, count - 1);
            }
            totalSum -= oldDataPoint.number;
        }
    }

    @Override
    public double getAverage() {
        if (dataPoints.isEmpty()) return 0;
        return totalSum / dataPoints.size();
    }

    @Override
    public double getHigher() {
        if (numberFrequency.isEmpty()) return 0;
        return numberFrequency.lastKey();
    }

    // A class to hold the data point information
    private static class DataPoint {
        long timestamp;
        double number;

        public DataPoint(long timestamp, double number) {
            this.timestamp = timestamp;
            this.number = number;
        }
    }
}
