package com.interview.notes.code.year.y2026.april.assessments.test7;

import java.util.ArrayList;
import java.util.List;

class Result {
    public static int manageServerLogs(List<String> serverLogs) {
        int days = 0, prev = -1, same = 0;

        for (String log : serverLogs) {
            int time = minuteOfDay(log);

            if (days == 0) {
                days = 1;
                prev = time;
                same = 1;
                continue;
            }

            if (time < prev) {
                days++;
                prev = time;
                same = 1;
                continue;
            }

            if (time == prev) {
                same++;
                if (same > 10) {
                    days++;
                    same = 1;
                }
                continue;
            }

            prev = time;
            same = 1;
        }

        return days;
    }

    static int minuteOfDay(String s) {
        int l = s.indexOf('[') + 1;
        int c = s.indexOf(':');
        int sp = s.indexOf(' ', c);

        int h = Integer.parseInt(s.substring(l, c));
        int m = Integer.parseInt(s.substring(c + 1, sp));
        boolean pm = Character.toLowerCase(s.charAt(sp + 1)) == 'p';

        if (h == 12) h = 0;

        return (pm ? h + 12 : h) * 60 + m;
    }
}

public class Solution {
    static String log(String time, String text) {
        return "[" + time + "]: " + text;
    }

    static void test(String name, List<String> logs, int expected) {
        int got = Result.manageServerLogs(logs);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL | got=" + got + " expected=" + expected));
    }

    static List<String> repeat(String time, int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(log(time, "Event" + i));
        return list;
    }

    public static void main(String[] args) {
        test("Sample Case 0", List.of(
                log("09:00 a.m.", "User logged in"),
                log("08:00 a.m.", "User logged in"),
                log("07:00 a.m.", "User logged in")
        ), 3);

        test("Sample Case 1", List.of(
                log("05:00 a.m.", "Server1 is started"),
                log("05:00 a.m.", "Server2 is started"),
                log("05:00 a.m.", "Server3 is started"),
                log("05:00 a.m.", "Server4 is started"),
                log("05:00 a.m.", "Server5 is started"),
                log("05:00 a.m.", "Server6 is started"),
                log("05:00 a.m.", "Server7 is started"),
                log("05:00 a.m.", "Server8 is started"),
                log("05:00 a.m.", "Server9 is started"),
                log("05:00 a.m.", "Server10 is started"),
                log("05:00 a.m.", "Server1 is interrupted")
        ), 2);

        test("Example", List.of(
                log("05:00 a.m.", "Server is started"),
                log("05:00 a.m.", "Rescan initialized"),
                log("01:13 p.m.", "Request processed"),
                log("01:10 p.m.", "Request processed"),
                log("11:40 p.m.", "Rescan completed")
        ), 2);

        test("Single", List.of(
                log("12:00 a.m.", "Only one")
        ), 1);

        test("Increasing Same Day", List.of(
                log("12:00 a.m.", "A"),
                log("12:01 a.m.", "B"),
                log("11:59 p.m.", "C")
        ), 1);

        test("Midnight Reset", List.of(
                log("11:59 p.m.", "A"),
                log("12:00 a.m.", "B")
        ), 2);

        test("Exactly Ten Same Minute", repeat("07:30 p.m.", 10), 1);
        test("Eleven Same Minute", repeat("07:30 p.m.", 11), 2);
        test("Twenty One Same Minute", repeat("07:30 p.m.", 21), 3);

        test("Twelve AM PM", List.of(
                log("12:00 a.m.", "A"),
                log("12:00 p.m.", "B"),
                log("11:59 p.m.", "C")
        ), 1);

        test("Backward After Overflow", List.of(
                log("05:00 a.m.", "A1"),
                log("05:00 a.m.", "A2"),
                log("05:00 a.m.", "A3"),
                log("05:00 a.m.", "A4"),
                log("05:00 a.m.", "A5"),
                log("05:00 a.m.", "A6"),
                log("05:00 a.m.", "A7"),
                log("05:00 a.m.", "A8"),
                log("05:00 a.m.", "A9"),
                log("05:00 a.m.", "A10"),
                log("05:00 a.m.", "A11"),
                log("04:59 a.m.", "B")
        ), 3);

        List<String> large = repeat("05:00 a.m.", 100000);
        test("Large Data", large, 10000);
    }
}