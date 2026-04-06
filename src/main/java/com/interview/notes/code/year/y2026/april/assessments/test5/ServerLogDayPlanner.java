package com.interview.notes.code.year.y2026.april.assessments.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ServerLogDayPlanner {
    public static int manageServerLogs(List<String> serverLogs) {
        int[][] cnt = new int[1440][11];
        TreeSet<Integer> active = new TreeSet<>();
        int days = 0;
        for (String s : serverLogs) {
            int t = minuteOfDay(s), k = 0;
            for (int i = 9; i >= 1; i--) {
                if (cnt[t][i] > 0) {
                    k = i;
                    break;
                }
            }
            if (k > 0) {
                cnt[t][k]--;
                if (--cnt[t][k + 1] == -1) cnt[t][k + 1] = 1;
                active.add(t);
                if (sum(cnt[t]) == 0) active.remove(t);
                continue;
            }
            Integer p = active.lower(t);
            if (p == null) {
                days++;
            } else {
                for (int i = 10; i >= 1; i--) {
                    if (cnt[p][i] > 0) {
                        cnt[p][i]--;
                        break;
                    }
                }
                if (sum(cnt[p]) == 0) active.remove(p);
            }
            cnt[t][1]++;
            active.add(t);
        }
        return days;
    }

    static int sum(int[] a) {
        int s = 0;
        for (int i = 1; i <= 10; i++) s += a[i];
        return s;
    }

    static int minuteOfDay(String s) {
        int h = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
        int m = (s.charAt(4) - '0') * 10 + s.charAt(5) - '0';
        boolean pm = s.charAt(8) == 'p';
        if (h == 12) h = 0;
        return (pm ? h + 12 : h) * 60 + m;
    }

    static String log(String t) {
        return "[" + t + "]: x";
    }

    static void test(String name, List<String> in, int expected) {
        int got = manageServerLogs(in);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL | got=" + got + " expected=" + expected));
    }

    static List<String> repeated(String time, int n) {
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
        test("ResetAtMidnight", List.of(log("11:59 p.m."), log("12:00 a.m.")), 2);
        test("ExactlyTenSameMinute", repeated("07:30 p.m.", 10), 1);
        test("ElevenSameMinute", repeated("07:30 p.m.", 11), 2);
        test("TwentyOneSameMinute", repeated("07:30 p.m.", 21), 3);
        test("MixedSameMinuteThenIncrease", List.of(
                log("07:30 p.m."), log("07:30 p.m."), log("07:31 p.m.")
        ), 1);
        test("DecreaseAfterSplit", List.of(
                log("10:00 a.m."), log("10:00 a.m."), log("09:59 a.m.")
        ), 2);
        test("TwelveAMAndPM", List.of(
                log("12:00 a.m."), log("12:00 p.m."), log("11:59 p.m.")
        ), 1);
        test("PMToAM", List.of(
                log("11:59 p.m."), log("12:00 a.m."), log("12:01 a.m.")
        ), 2);

        List<String> large = new ArrayList<>();
        for (int i = 0; i < 100000; i++) large.add(log("05:00 a.m."));
        test("LargeData", large, 10000);

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = Integer.parseInt(sc.nextLine().trim());
            List<String> input = new ArrayList<>(n);
            for (int i = 0; i < n; i++) input.add(sc.nextLine());
            System.out.println(manageServerLogs(input));
        }
    }
}