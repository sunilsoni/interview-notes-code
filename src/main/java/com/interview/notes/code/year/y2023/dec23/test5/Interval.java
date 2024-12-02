package com.interview.notes.code.year.y2023.dec23.test5;

public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start value must not be greater than end value");
        }
        this.start = start;
        this.end = end;
    }

    public boolean contains(T value) {
        return (value.compareTo(start) >= 0) && (value.compareTo(end) <= 0);
    }

    @Override
    public String toString() {
        return "Interval[start=" + start + ", end=" + end + ']';
    }

    // ... (equals, hashCode methods if needed)
}
