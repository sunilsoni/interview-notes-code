package com.interview.notes.code.months.year2023.dec23.test6;

import java.util.ArrayList;
import java.util.Collection;

public class IntervalFinder {
    public static Collection<Interval> findIntervalsContainingPoint(Collection<Interval> intervals, int point) {
        Collection<Interval> result = new ArrayList<>();

        for (Interval interval : intervals) {
            if (interval.contains(point)) {
                result.add(interval);
            }
        }

        return result;
    }
}