package com.interview.notes.code.year.y2026.april.assessments.test9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumCpuCoresAllocator {

    public static int getMinCores(List<Integer> start, List<Integer> end) {
        Collections.sort(start);
        Collections.sort(end);
        
        var cores = 0;
        var j = 0;
        
        for (var s : start) {
            if (s <= end.get(j)) {
                cores++;
            } else {
                j++;
            }
        }
        
        return cores;
    }

    public static void main(String[] args) {
        test(List.of(1, 3, 4), List.of(3, 5, 6), 2);
        
        var largeStart = IntStream.range(1, 100001).boxed().collect(Collectors.toList());
        var largeEnd = IntStream.range(2, 100002).boxed().collect(Collectors.toList());
        test(largeStart, largeEnd, 2);
    }

    private static void test(List<Integer> start, List<Integer> end, int expected) {
        var mutableStart = new ArrayList<>(start);
        var mutableEnd = new ArrayList<>(end);
        var result = getMinCores(mutableStart, mutableEnd);
        System.out.println(result == expected ? "PASS" : "FAIL");
    }
}