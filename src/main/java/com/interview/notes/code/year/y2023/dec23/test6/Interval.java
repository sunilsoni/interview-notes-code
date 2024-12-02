package com.interview.notes.code.year.y2023.dec23.test6;

public class Interval {
    private double start;
    private double end;

    public Interval(double start, double end) {
        if (start > end) {
            throw new IllegalArgumentException("Start value must be less than or equal to end value.");
        }
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        Interval interval1 = new Interval(1.0, 5.0);
        Interval interval2 = new Interval(4.0, 7.0);

        System.out.println("Interval 1: " + interval1);
        System.out.println("Interval 2: " + interval2);

        System.out.println("Length of Interval 1: " + interval1.length());
        System.out.println("Length of Interval 2: " + interval2.length());

        System.out.println("Does Interval 1 contain 3.0? " + interval1.contains(3.0));
        System.out.println("Do Interval 1 and Interval 2 overlap? " + interval1.overlaps(interval2));
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public double length() {
        return end - start;
    }

    public boolean contains(double value) {
        return value >= start && value <= end;
    }

    public boolean overlaps(Interval other) {
        return this.start <= other.end && other.start <= this.end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
