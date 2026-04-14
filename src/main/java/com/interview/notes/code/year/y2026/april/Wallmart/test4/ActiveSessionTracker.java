package com.interview.notes.code.year.y2026.april.Wallmart.test4;

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
                Set.of("UserC", "UserD")
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
            .filter(arr -> arr.length >= 4)
            .collect(
                HashSet::new,
                (set, arr) -> {
                    String user = arr[1];
                    String action = arr[2];
                    String status = arr[3];

                    if ("LOGOUT".equals(action) || "ERROR".equals(action) || "FAIL".equals(status)) {
                        set.remove(user);
                    } else if ("LOGIN".equals(action) && "SUCCESS".equals(status)) {
                        set.add(user);
                    }
                },
                HashSet::addAll
            );
    }

    record TestCase(List<String> logs, Set<String> expected) {}
}