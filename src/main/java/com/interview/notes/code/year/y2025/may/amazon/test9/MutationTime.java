package com.interview.notes.code.year.y2025.may.amazon.test9;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MutationTime {

    /**
     * In one pass over genome, record the indices of each mutation.
     * The time to exhaustively delete is the maximum gap between
     * consecutive mutations (and the distance of the first mutation
     * from index 0).
     */
    public static int findTime(String genome, char mutation) {
        int prev = -1;
        int maxTime = 0;
        int n = genome.length();

        for (int i = 0; i < n; i++) {
            if (genome.charAt(i) == mutation) {
                if (prev == -1) {
                    // first mutation: it can remove up to i non-mutation chars
                    maxTime = i;
                } else {
                    // this mutation deletes the gap since the last one
                    maxTime = Math.max(maxTime, i - prev);
                }
                prev = i;
            }
        }
        return maxTime;
    }

    public static void main(String[] args) {
        String[] genomes = {
            "tamem",
            "momoz",
            "luvzliz",
            "aaaa",
            "bbb",
            // the two you reported as “failing”:
            "ttttttttttttttqtqtqqttttttqttttqtttqttqtqq",
            String.join("", java.util.Collections.nCopies(500, "q"))
        };
        char[] muts = { 'm', 'm', 'z', 'a', 'b', 'q', 'q' };
        int[] exps  = {   2,   2,   3,   1,   1,    2,     1  };

        AtomicInteger passed = new AtomicInteger();
        IntStream.range(0, genomes.length).forEach(i -> {
            int res = findTime(genomes[i], muts[i]);
            if (res == exps[i]) {
                System.out.println("PASS: [" + genomes[i].substring(0, Math.min(20, genomes[i].length()))
                                   + (genomes[i].length()>20 ? "…" : "")
                                   + "] → " + res);
                passed.getAndIncrement();
            } else {
                System.out.println("FAIL: [" + genomes[i].substring(0, Math.min(20, genomes[i].length()))
                                   + (genomes[i].length()>20 ? "…" : "")
                                   + "] → got " + res + " but expected " + exps[i]);
            }
        });
        System.out.println("Passed " + passed + " out of " + genomes.length + " tests\n");

        // one more large-stress test
        int N = 100_000;
        String big = String.join("", java.util.Collections.nCopies(N, "q"));
        long t0 = System.currentTimeMillis();
        int ans = findTime(big, 'q');
        long dt = System.currentTimeMillis() - t0;
        System.out.println("All-q length " + N + " → time=" + ans + "  in " + dt + " ms");
    }
}