package com.interview.notes.code.year.y2026.april.assessments.test3;

import java.util.List;
import java.util.TreeSet;

class Result {
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
        for (int i = from; i <= to; i++) {
            if (a[i] > 0) return i;
        }
        return -1;
    }

    static int minuteOfDay(String s) {
        // FIX: do not use fixed indexes for a.m./p.m. parsing
        int l = s.indexOf('[') + 1;
        int c = s.indexOf(':');
        int sp = s.indexOf(' ', c);
        int r = s.indexOf(']');

        // FIX: parse hour/minute safely
        int h = Integer.parseInt(s.substring(l, c));
        int m = Integer.parseInt(s.substring(c + 1, sp));

        // FIX: hidden tests may vary, so check only first letter of am/pm part
        boolean pm = Character.toLowerCase(s.charAt(sp + 1)) == 'p';

        // FIX: 12 a.m. -> 00, 12 p.m. -> 12
        if (h == 12) h = 0;

        // FIX: result always stays in 0..1439
        return (pm ? h + 12 : h) * 60 + m;
    }
}