package com.interview.notes.code.year.y2023.dec23.test5;

import java.util.ArrayList;
import java.util.Collection;

public class IntervalFinder<T extends Comparable<T>> {
    public Collection<Interval<T>> findIntervalsContainingPoint(Collection<Interval<T>> intervals, T point) {
        Collection<Interval<T>> result = new ArrayList<>();

        for (Interval<T> interval : intervals) {
            if (interval.contains(point)) {
                result.add(interval);
            }
        }

        return result;
    }
}
