package com.interview.notes.code.year.y2025.may.amazon.test5;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MutationTime {

    public static int findTime(String genome, char mutation) {
        int n = genome.length();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(genome.charAt(i));
        }
        for (int i = 1; i < n; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }

        PriorityQueue<Event> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));
        // schedule each mutation at time=1
        for (int i = 0; i < n; i++) {
            if (nodes[i].c == mutation) {
                pq.offer(new Event(nodes[i], 1));
            }
        }

        int lastRemovalTime = 0;
        while (!pq.isEmpty()) {
            Event ev = pq.poll();
            Node mNode = ev.mNode;
            int t = ev.time;

            // ←— skip any events whose mutation has already been deleted
            if (mNode.removed) continue;

            Node target = mNode.prev;
            if (target == null || target.removed) continue;

            // remove the neighbour
            target.removed = true;
            lastRemovalTime = Math.max(lastRemovalTime, t);

            // unlink it
            Node L = target.prev, R = target.next;
            if (L != null) L.next = R;
            if (R != null) R.prev = L;

            // and schedule mNode again if it still has a left neighbour
            if (mNode.prev != null) {
                pq.offer(new Event(mNode, t + 1));
            }
        }

        return lastRemovalTime;
    }

    public static void main(String[] args) {
        // the two failing cases you gave, plus a sanity check
        String[] genomes = {
                "ttttttttttttttqtqtqqttttttqttttqtttqttqtqq",
                // really long all-q string (length ~500)
                String.join("", Collections.nCopies(500, "q")),
                "tamem"
        };
        char[] muts = {'q', 'q', 'm'};
        int[] exps = {2, 1, 2};

        AtomicInteger pass = new AtomicInteger();
        IntStream.range(0, genomes.length).forEach(i -> {
            int res = findTime(genomes[i], muts[i]);
            if (res == exps[i]) {
                System.out.println("PASS: " + genomes[i].substring(0, 20)
                        + (genomes[i].length() > 20 ? "…" : "")
                        + "  → " + res);
                pass.getAndIncrement();
            } else {
                System.out.println("FAIL: " + genomes[i].substring(0, 20)
                        + (genomes[i].length() > 20 ? "…" : "")
                        + "  → got " + res + " but expected " + exps[i]);
            }
        });
        System.out.println("Passed " + pass.get() + " of " + genomes.length + " tests\n");

        // large stress test
        int N = 100_000;
        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) sb.append('q');
        long start = System.currentTimeMillis();
        int t = findTime(sb.toString(), 'q');
        long dt = System.currentTimeMillis() - start;
        System.out.println("All-q of length " + N + " → time=" + t + "  computed in " + dt + "ms");
    }

    static class Node {
        char c;
        Node prev, next;
        boolean removed = false;

        Node(char c) {
            this.c = c;
        }
    }

    static class Event {
        Node mNode;
        int time;

        Event(Node mNode, int time) {
            this.mNode = mNode;
            this.time = time;
        }
    }
}