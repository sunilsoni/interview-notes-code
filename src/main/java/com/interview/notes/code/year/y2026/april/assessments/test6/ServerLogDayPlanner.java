package com.interview.notes.code.year.y2026.april.assessments.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ServerLogDayPlanner {
    public static int manageServerLogs(List<String> serverLogs) {
        int[][] counts = new int[1440][11];
        int[] totals = new int[1440];
        TreeSet<Integer> active = new TreeSet<>();
        int days = 0;

        for (String log : serverLogs) {
            int time = minuteOfDay(log);
            int same = find(counts[time], 1, 9);

            if (same != -1) {
                counts[time][same]--;
                counts[time][same + 1]++;
                continue;
            }

            Integer prev = active.lower(time);
            if (prev == null) {
                days++;
            } else {
                int bucket = find(counts[prev], 1, 10);
                counts[prev][bucket]--;
                if (--totals[prev] == 0) active.remove(prev);
            }

            counts[time][1]++;
            if (totals[time]++ == 0) active.add(time);
        }

        return days;
    }

    static int find(int[] a, int from, int to) {
        for (int i = from; i <= to; i++) if (a[i] > 0) return i;
        return -1;
    }

    static int minuteOfDay(String s) {
        int h = (s.charAt(1) - '0') * 10 + (s.charAt(2) - '0');
        int m = (s.charAt(4) - '0') * 10 + (s.charAt(5) - '0');
        boolean pm = s.charAt(7) == 'p';
        if (h == 12) h = 0;
        return (pm ? h + 12 : h) * 60 + m;
    }

    static String log(String t) {
        return "[" + t + "]: x";
    }

    static void test(String name, List<String> input, int expected) {
        int got = manageServerLogs(input);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL | got=" + got + " expected=" + expected));
    }

    static List<String> repeat(String time, int n) {
        List<String> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(log(time));
        return list;
    }

    public static void main(String[] args) {
        test("Sample0", List.of(
                log("09:00 a.m."),
                log("08:00 a.m."),
                log("07:00 a.m.")
        ), 3);

        test("Sample1", List.of(
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("05:00 a.m.")
        ), 2);

        test("Example", List.of(
                log("05:00 a.m."),
                log("05:00 a.m."),
                log("01:13 p.m."),
                log("01:10 p.m."),
                log("11:40 p.m.")
        ), 2);

        test("Single", List.of(log("12:00 a.m.")), 1);
        test("Increasing", List.of(log("12:00 a.m."), log("12:01 a.m."), log("11:59 p.m.")), 1);
        test("MidnightSplit", List.of(log("11:59 p.m."), log("12:00 a.m.")), 2);
        test("TenSameMinute", repeat("07:30 p.m.", 10), 1);
        test("ElevenSameMinute", repeat("07:30 p.m.", 11), 2);
        test("TwentyOneSameMinute", repeat("07:30 p.m.", 21), 3);
        test("AMPM", List.of(log("12:00 a.m."), log("12:00 p.m."), log("11:59 p.m.")), 1);
        test("Mixed", List.of(
                log("10:00 a.m."),
                log("10:00 a.m."),
                log("09:59 a.m."),
                log("10:01 a.m.")
        ), 2);

        List<String> large = repeat("05:00 a.m.", 100000);
        test("Large", large, 10000);

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = Integer.parseInt(sc.nextLine().trim());
            List<String> input = new ArrayList<>(n);
            for (int i = 0; i < n; i++) input.add(sc.nextLine());
            System.out.println(manageServerLogs(input));
        }
    }
}