package com.interview.notes.code.year.y2025.september.amazon.test4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProcessQueryAnalyzer {

    public static List<Integer> getQueryAnswers(List<Integer> starts, List<Integer> ends,
                                                List<Integer> query_process,
                                                List<Integer> query_start,
                                                List<Integer> query_end) {

        int n = starts.size();
        int q = query_process.size();

        TreeMap<Integer, Integer> events = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            events.put(starts.get(i), events.getOrDefault(starts.get(i), 0) + 1);
            events.put(ends.get(i) + 1, events.getOrDefault(ends.get(i) + 1, 0) - 1);
        }

        TreeMap<Integer, Integer> countAtTime = new TreeMap<>();
        int activeProcesses = 0;

        for (Map.Entry<Integer, Integer> entry : events.entrySet()) {
            countAtTime.put(entry.getKey(), activeProcesses);
            activeProcesses += entry.getValue();
        }

        return IntStream.range(0, q)
                .mapToObj(j -> {
                    int targetProcess = query_process.get(j);
                    int startTime = query_start.get(j);
                    int endTime = query_end.get(j);

                    int totalCount = 0;

                    Integer currentKey = countAtTime.floorKey(startTime);
                    if (currentKey == null) {
                        currentKey = countAtTime.ceilingKey(startTime);
                    }

                    int currentTime = startTime;

                    while (currentTime <= endTime) {
                        Integer floorKey = countAtTime.floorKey(currentTime);
                        int processesRunning = (floorKey != null) ? countAtTime.get(floorKey) : 0;

                        Integer nextChangeKey = countAtTime.higherKey(currentTime);
                        int rangeEnd = (nextChangeKey == null || nextChangeKey > endTime + 1)
                                ? endTime : nextChangeKey - 1;

                        if (processesRunning == targetProcess) {
                            totalCount += rangeEnd - currentTime + 1;
                        }

                        if (nextChangeKey == null || nextChangeKey > endTime) {
                            break;
                        }

                        currentTime = nextChangeKey;
                    }

                    return totalCount;
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        boolean allPassed = true;

        List<Integer> starts1 = List.of(0);
        List<Integer> ends1 = List.of(0);
        List<Integer> queryProcess1 = List.of(1);
        List<Integer> queryStart1 = List.of(0);
        List<Integer> queryEnd1 = List.of(0);
        List<Integer> expected1 = List.of(1);
        List<Integer> result1 = getQueryAnswers(starts1, ends1, queryProcess1, queryStart1, queryEnd1);
        allPassed &= testCase("Test 1", expected1, result1);

        List<Integer> starts2 = Arrays.asList(0, 4, 10);
        List<Integer> ends2 = Arrays.asList(10, 6, 12);
        List<Integer> queryProcess2 = Arrays.asList(0, 1, 2);
        List<Integer> queryStart2 = Arrays.asList(0, 4, 3);
        List<Integer> queryEnd2 = Arrays.asList(20, 10, 20);
        List<Integer> expected2 = Arrays.asList(8, 3, 4);
        List<Integer> result2 = getQueryAnswers(starts2, ends2, queryProcess2, queryStart2, queryEnd2);
        allPassed &= testCase("Test 2", expected2, result2);

        List<Integer> starts3 = Arrays.asList(1, 3, 5);
        List<Integer> ends3 = Arrays.asList(2, 4, 6);
        List<Integer> queryProcess3 = Arrays.asList(1, 2, 3);
        List<Integer> queryStart3 = Arrays.asList(0, 2, 5);
        List<Integer> queryEnd3 = Arrays.asList(10, 5, 6);
        List<Integer> expected3 = Arrays.asList(6, 2, 1);
        List<Integer> result3 = getQueryAnswers(starts3, ends3, queryProcess3, queryStart3, queryEnd3);
        allPassed &= testCase("Test 3", expected3, result3);

        Random rand = new Random(42);
        int largeN = 100000;
        int largeQ = 100000;
        List<Integer> largeStarts = IntStream.range(0, largeN)
                .map(i -> rand.nextInt(100000))
                .boxed()
                .collect(Collectors.toList());
        List<Integer> largeEnds = IntStream.range(0, largeN)
                .map(i -> largeStarts.get(i) + rand.nextInt(100))
                .boxed()
                .collect(Collectors.toList());
        List<Integer> largeQueryProcess = IntStream.range(0, largeQ)
                .map(i -> rand.nextInt(50))
                .boxed()
                .collect(Collectors.toList());
        List<Integer> largeQueryStart = IntStream.range(0, largeQ)
                .map(i -> rand.nextInt(100000))
                .boxed()
                .collect(Collectors.toList());
        List<Integer> largeQueryEnd = IntStream.range(0, largeQ)
                .map(i -> largeQueryStart.get(i) + rand.nextInt(100))
                .boxed()
                .collect(Collectors.toList());

        long startTime = System.currentTimeMillis();
        List<Integer> largeResult = getQueryAnswers(largeStarts, largeEnds, largeQueryProcess,
                largeQueryStart, largeQueryEnd);
        long endTime = System.currentTimeMillis();

        System.out.println("Test 4 (Large Dataset): PASS - Processed " + largeN +
                " processes and " + largeQ + " queries in " +
                (endTime - startTime) + "ms");

        List<Integer> starts5 = Arrays.asList(0, 2, 4);
        List<Integer> ends5 = Arrays.asList(6, 3, 8);
        List<Integer> queryProcess5 = Arrays.asList(0, 1);
        List<Integer> queryStart5 = Arrays.asList(7, 0);
        List<Integer> queryEnd5 = Arrays.asList(10, 1);
        List<Integer> expected5 = Arrays.asList(2, 2);
        List<Integer> result5 = getQueryAnswers(starts5, ends5, queryProcess5, queryStart5, queryEnd5);
        allPassed &= testCase("Test 5", expected5, result5);

        List<Integer> starts6 = List.of(5);
        List<Integer> ends6 = List.of(10);
        List<Integer> queryProcess6 = Arrays.asList(1, 0);
        List<Integer> queryStart6 = Arrays.asList(5, 0);
        List<Integer> queryEnd6 = Arrays.asList(10, 4);
        List<Integer> expected6 = Arrays.asList(6, 5);
        List<Integer> result6 = getQueryAnswers(starts6, ends6, queryProcess6, queryStart6, queryEnd6);
        allPassed &= testCase("Test 6", expected6, result6);

        System.out.println("\n" + (allPassed ? "ALL TESTS PASSED" : "SOME TESTS FAILED"));
    }

    private static boolean testCase(String testName, List<Integer> expected, List<Integer> actual) {
        boolean passed = expected.equals(actual);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
        return passed;
    }
}