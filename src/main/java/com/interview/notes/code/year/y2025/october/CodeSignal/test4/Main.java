package com.interview.notes.code.year.y2025.october.CodeSignal.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        boolean[] closed = new boolean[n];
        int[] remain = Arrays.copyOf(centerCapacities, n);
        int[] done = new int[n];
        int cur = 0;

        for (String op : dailyLog) {
            if (op.startsWith("CLOSURE")) {
                int idx = Integer.parseInt(op.split("\\s+")[1]);
                if (!closed[idx]) { closed[idx] = true; remain[idx] = 0; }
            } else {
                while (true) {
                    int k = -1;
                    for (int j = 0; j < n; j++) {
                        int idx = (cur + j) % n;
                        if (!closed[idx] && remain[idx] > 0) { k = idx; break; }
                    }
                    if (k == -1) {
                        for (int i = 0; i < n; i++) if (!closed[i]) remain[i] = centerCapacities[i];
                        cur = 0;
                        continue;
                    }
                    done[k]++; remain[k]--; cur = k;
                    if (remain[k] == 0) cur = (k + 1) % n;
                    break;
                }
            }
        }

        int max = Arrays.stream(done).max().orElse(0);
        int ans = -1;
        for (int i = 0; i < n; i++) if (done[i] == max) ans = i;
        return ans;
    }

    static int reference(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        boolean[] closed = new boolean[n];
        int[] remain = Arrays.copyOf(centerCapacities, n);
        int[] done = new int[n];
        int cur = 0;

        for (String op : dailyLog) {
            if (op.startsWith("CLOSURE")) {
                int idx = Integer.parseInt(op.split("\\s+")[1]);
                closed[idx] = true; remain[idx] = 0;
            } else {
                int tries = 0;
                while (tries++ < 2) {
                    int walked = 0;
                    while (walked < n && (closed[cur] || remain[cur] == 0)) { cur = (cur + 1) % n; walked++; }
                    if (walked == n) {
                        for (int i = 0; i < n; i++) if (!closed[i]) remain[i] = centerCapacities[i];
                        cur = 0;
                        continue;
                    }
                    done[cur]++; remain[cur]--;
                    if (remain[cur] == 0) cur = (cur + 1) % n;
                    break;
                }
            }
        }

        int max = Arrays.stream(done).max().orElse(0);
        int ans = -1;
        for (int i = 0; i < n; i++) if (done[i] == max) ans = i;
        return ans;
    }

    static void run(String name, int[] caps, String[] log, int expected) {
        int got = solution(caps, log);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL") + " expected=" + expected + " got=" + got);
    }

    static void runEq(String name, int[] caps, String[] log) {
        int a = solution(caps, log);
        int b = reference(caps, log);
        System.out.println(name + ": " + (a == b ? "PASS" : "FAIL") + " sol=" + a + " ref=" + b);
    }

    public static void main(String[] args) {
        run("Ex", new int[]{1,2,1,2,1}, new String[]{"PACKAGE","PACKAGE","CLOSURE 2","PACKAGE","CLOSURE 3","PACKAGE","PACKAGE"}, 1);

        runEq("Basic1", new int[]{2,1}, new String[]{"PACKAGE","PACKAGE","PACKAGE"});
        runEq("Basic2", new int[]{3}, new String[]{"PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE"});
        runEq("WithClosure", new int[]{2,2,2}, new String[]{"PACKAGE","CLOSURE 0","PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE"});
        runEq("ResetLoop", new int[]{1,1,1}, new String[]{"PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE"});
        runEq("SkipClosed", new int[]{1,3,2,2}, new String[]{"CLOSURE 1","PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE"});
        runEq("CloseCurrent", new int[]{3,3,3}, new String[]{"PACKAGE","PACKAGE","CLOSURE 0","PACKAGE","PACKAGE","PACKAGE","PACKAGE","PACKAGE"});

        Random rnd = new Random(123);
        for (int t = 1; t <= 20; t++) {
            int n = 1 + rnd.nextInt(8);
            int[] caps = IntStream.range(0, n).map(i -> 1 + rnd.nextInt(5)).toArray();
            List<String> ops = new ArrayList<>();
            for (int i = 0; i < 200 + rnd.nextInt(800); i++) {
                if (rnd.nextInt(7) == 0) ops.add("CLOSURE " + rnd.nextInt(n));
                else ops.add("PACKAGE");
            }
            runEq("Random"+t, caps, ops.toArray(new String[0]));
        }

        int n = 100;
        int[] caps = IntStream.range(0, n).map(i -> 1 + (i % 5)).toArray();
        String[] big = new String[1000];
        Arrays.fill(big, "PACKAGE");
        System.out.println("LargeResult=" + solution(caps, big));
    }
}
