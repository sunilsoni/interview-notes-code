package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MutationTime {

    // Simple node in a doubly‐linked list, tracking if it's gone yet.
    static class Node {
        char c;
        Node prev, next;
        boolean removed = false;
        Node(char c) { this.c = c; }
    }

    // An event: at time t, this mutated node will try to remove its left neighbor.
    static class Event {
        Node mNode;
        int time;
        Event(Node mNode, int time) { this.mNode = mNode; this.time = time; }
    }

    // Computes the time until no more removals happen.
    public static int findTime(String genome, char mutation) {
        int n = genome.length();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node(genome.charAt(i));

        // link them up
        for (int i = 1; i < n; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }

        // min‐heap of events by time
        PriorityQueue<Event> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));
        // schedule each initial mutation to act at t=1
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
            Node target = mNode.prev;
            // if there's no neighbor or it's already gone, skip
            if (target == null || target.removed) continue;

            // remove it now
            target.removed = true;
            lastRemovalTime = Math.max(lastRemovalTime, t);

            // unlink it from the list
            Node L = target.prev, R = target.next;
            if (L != null) L.next = R;
            if (R != null) R.prev = L;

            // schedule this same mutated node to act again next time
            if (mNode.prev != null) {
                pq.offer(new Event(mNode, t + 1));
            }
        }

        return lastRemovalTime;
    }

    public static void main(String[] args) {
        String[] genomes   = { "tamem", "momoz", "luvzliz", "aaaa", "bbb" };
        char[]   mutations = {   'm',     'm',      'z',    'a',   'b'  };
        int[]    expecteds = {     2,       2,        3,      0,     0  };

        AtomicInteger passed = new AtomicInteger();

        // test all cases
        IntStream.range(0, genomes.length).forEach(i -> {
            String g = genomes[i];
            char m   = mutations[i];
            int  exp = expecteds[i];
            int  res = findTime(g, m);
            if (res == exp) {
                System.out.println("PASS: \"" + g + "\" with '" + m + "' -> " + res);
                passed.getAndIncrement();
            } else {
                System.out.println("FAIL: \"" + g + "\" with '" + m + "' -> got " + res + " but expected " + exp);
            }
        });

        System.out.println("Passed " + passed.get() + " out of " + genomes.length + " tests\n");

        // large random‐pattern test to check speed
        int largeN = 100_000;
        StringBuilder sb = new StringBuilder(largeN);
        char bigMutation = 'x';
        for (int i = 0; i < largeN; i++) {
            // alternating so every even index is 'x', odd is 'y'
            sb.append((i % 2 == 0) ? bigMutation : 'y');
        }
        String largeGenome = sb.toString();

        long start = System.currentTimeMillis();
        int largeTime = findTime(largeGenome, bigMutation);
        long dur   = System.currentTimeMillis() - start;

        System.out.println("Large input (n=" + largeN + "): time=" + largeTime + " units, computed in " + dur + " ms");
    }
}