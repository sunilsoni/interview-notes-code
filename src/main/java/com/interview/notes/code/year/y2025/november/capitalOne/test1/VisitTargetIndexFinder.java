package com.interview.notes.code.year.y2025.november.capitalOne.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VisitTargetIndexFinder {

    public static void main(String[] args) {
        VisitTargetIndexFinder finder = new VisitTargetIndexFinder();

        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase(new int[]{300, 200, 100, 200, 500}, 700, 3));
        tests.add(new TestCase(new int[]{900, 821, 1090}, 900, 0));
        tests.add(new TestCase(new int[]{700, 800, 500}, 2001, -1));
        tests.add(new TestCase(new int[]{}, 1, -1));
        tests.add(new TestCase(new int[]{500}, 400, 0));
        tests.add(new TestCase(new int[]{500}, 600, -1));

        int bigSize = 1000;
        int[] bigVisits = IntStream.generate(() -> 10).limit(bigSize).toArray();
        tests.add(new TestCase(bigVisits, 5000, 499));

        int caseNo = 1;
        for (TestCase t : tests) {
            int result = finder.solution(t.visits(), t.target());
            boolean ok = result == t.expected();
            System.out.println("Case " + caseNo++ + ": " + (ok ? "PASS" : "FAIL (expected " + t.expected() + " got " + result + ")"));
        }
    }

    public int solution(int[] visits, int target) {
        int sum = 0;
        for (int i = 0; i < visits.length; i++) {
            sum += visits[i];
            if (sum >= target) {
                return i;
            }
        }
        return -1;
    }

    private record TestCase(int[] visits, int target, int expected) {
    }
}
