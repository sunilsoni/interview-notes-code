package com.interview.notes.code.year.y2025.may.amazon.test9;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class GenomeMutation {

    public static int findTime(String genome, char mut) {
        int n = genome.length();
        // -1 = not removed yet; otherwise the time it was removed
        int[] removedAt = new int[n];
        Arrays.fill(removedAt, -1);

        // priority queue of wavefronts, ordered by earliest time first
        PriorityQueue<Wave> pq = new PriorityQueue<>();

        // seed: every mutation at t=0, its first target is idx-1
        for (int i = 0; i < n; i++) {
            if (genome.charAt(i) == mut) {
                pq.add(new Wave(1, i, i - 1));
            }
        }

        int lastRemovalTime = 0;

        while (!pq.isEmpty()) {
            Wave w = pq.poll();
            int t = w.time, org = w.origin, tgt = w.nextTarget;

            // if out of bounds, or already removed, or the origin itself got removed before,
            // skip this front:
            if (tgt < 0 || removedAt[tgt] != -1 || removedAt[org] != -1)
                continue;

            // remove tgt at time t
            removedAt[tgt] = t;
            lastRemovalTime = Math.max(lastRemovalTime, t);

            // if the removed position was itself a mutation, that mutation
            // can no longer send waves of its own, but the original origin continues
            // (we only cancel propagation if the *origin* itself gets removed)

            // propagate this wave one more step to the left:
            pq.add(new Wave(t + 1, org, tgt - 1));
        }

        return lastRemovalTime;
    }

    public static void main(String[] args) {
        class Test {
            final String g;
            final char m;
            final int e;

            Test(String g, char m, int e) {
                this.g = g;
                this.m = m;
                this.e = e;
            }
        }
        List<Test> tests = Arrays.asList(
                new Test("tamem", 'm', 2),
                new Test("momoz", 'm', 2),
                new Test("luvzliz", 'z', 3),
                new Test("aaaaa", 'a', 4),
                new Test("xxxxx", 'm', 0),
                new Test("m", 'm', 0)
        );

        // large test
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) sb.append('a');
        sb.append('m');
        tests.add(new Test(sb.toString(), 'm', 100_000));

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int got = findTime(t.g, t.m);
            boolean ok = got == t.e;
            System.out.printf("Test %2d: %s (got=%d, expected=%d)%n",
                    i + 1, ok ? "PASS " : "FAIL ", got, t.e);
            if (ok) passed++;
        }
        System.out.printf("%n%d/%d tests passed.%n", passed, tests.size());
    }

    static class Wave implements Comparable<Wave> {
        int time;       // when this wavefront reaches nextTarget
        int origin;     // original index of the mutation that spawned it
        int nextTarget; // the index it will remove at `time`

        Wave(int time, int origin, int nextTarget) {
            this.time = time;
            this.origin = origin;
            this.nextTarget = nextTarget;
        }

        @Override
        public int compareTo(Wave o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
