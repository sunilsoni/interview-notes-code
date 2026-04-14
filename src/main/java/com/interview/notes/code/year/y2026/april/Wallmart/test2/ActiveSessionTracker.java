package com.interview.notes.code.year.y2026.april.Wallmart.test2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActiveSessionTracker {

    public static void main(String[] args) {
        List<TestCase> tests = List.of(
            new TestCase(
                List.of(
                    "10:00 Ramya LOGIN SUCCESS",
                    "10:05 Raja LOGIN SUCCESS",
                    "10:10 Ramya LOGOUT SUCCESS",
                    "10:15 UserC LOGIN SUCCESS",
                    "10:20 Raja ERROR FAIL",
                    "10:25 UserD LOGIN SUCCESS"
                ),
                Set.of("Raja", "UserC", "UserD")
            ),
            new TestCase(
                List.of(
                    "09:00 Sunil LOGIN SUCCESS",
                    "09:30 Sunil LOGOUT FAIL",
                    "10:00 Oishi LOGIN SUCCESS",
                    "10:15 Oishi LOGOUT SUCCESS",
                    "10:30 Oishi LOGOUT FAIL"
                ),
                Set.of("Sunil")
            )
        );

        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            Set<String> actual = getActiveUsers(tc.logs());
            boolean passed = actual.equals(tc.expected());
            System.out.println("Test " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") + " | Active: " + actual);
        }
    }

    public static Set<String> getActiveUsers(List<String> logs) {
        return logs.stream()
            .map(log -> log.split(" "))
            .filter(arr -> arr.length >= 4 && "SUCCESS".equals(arr[3]))
            .collect(
                HashSet::new,
                (set, arr) -> {
                    if ("LOGIN".equals(arr[2])) set.add(arr[1]);
                    if ("LOGOUT".equals(arr[2])) set.remove(arr[1]);
                },
                HashSet::addAll
            );
    }

    record TestCase(List<String> logs, Set<String> expected) {}
}