package com.interview.notes.code.year.y2026.march.common.test2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumChocolateWeightCalculator {

    public static int findMinWeight(List<Integer> weights, int d) {
        var priorityQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        priorityQueue.addAll(weights);
        
        var totalWeight = weights.stream().mapToInt(Integer::intValue).sum();

        for (var i = 0; i < d; i++) {
            var heaviest = priorityQueue.poll();
            
            if (heaviest <= 1) {
                break;
            }
            
            var eaten = heaviest / 2;
            totalWeight -= eaten;
            priorityQueue.add(heaviest - eaten);
        }

        return totalWeight;
    }

    public static void main(String[] args) {
        record TestCase(List<Integer> weights, int d, int expected) {}

        var testCases = List.of(
            new TestCase(List.of(30, 20, 25), 4, 31),
            new TestCase(List.of(2), 1, 1),
            new TestCase(List.of(2, 3), 1, 4),
            new TestCase(Collections.nCopies(100000, 10000), 2000000, 100000)
        );

        var passedCount = 0;
        
        for (var i = 0; i < testCases.size(); i++) {
            var testCase = testCases.get(i);
            var startTime = System.currentTimeMillis();
            
            var result = findMinWeight(testCase.weights(), testCase.d());
            
            var endTime = System.currentTimeMillis();
            var status = (result == testCase.expected()) ? "PASS" : "FAIL";
            
            if ("PASS".equals(status)) {
                passedCount++;
            }
            
            System.out.printf("Test Case %d: %s | Expected: %d | Got: %d | Time: %dms%n", 
                i + 1, status, testCase.expected(), result, (endTime - startTime));
        }
        
        System.out.printf("Total Passed: %d/%d%n", passedCount, testCases.size());
    }
}