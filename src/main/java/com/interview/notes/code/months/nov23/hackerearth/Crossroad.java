package com.interview.notes.code.months.nov23.hackerearth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class Crossroad {

    public static void main(String[] args) {
        int n = 3;
        int[][] a = {
                {1, 0, 1, 1},
                {2, 1, 0, 0},
                {3, 0, 0, 5}
        };

        List<int[]> sequence = solve(n, a);
        for (int[] pair : sequence) {
            int road = pair[0];
            int time = pair[1];
            System.out.println(road + " " + time);
        }
    }


    private static List<int[]> solve(int n, int[][] a) {
        int[] waitingTime = new int[n];
        for (int i = 0; i < n; i++) {
            waitingTime[i] = 0;
            for (int j = 0; j < 4; j++) {
                if (a[i][j] > 0) {
                    waitingTime[i] += a[i][j];
                }
            }
        }

        List<int[]> sequence = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a1, b) -> a1.first - b.first);
        for (int i = 0; i < n; i++) {
            pq.add(new Pair(waitingTime[i], i));
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int waitingTime1 = pair.first;
            int road = pair.second;

            sequence.add(new int[]{road, waitingTime1});
            for (int i = 0; i < n; i++) {
                if (i != road) {
                    waitingTime[i] -= Math.min(3, a[i][0] + a[i][1]);
                    if (waitingTime[i] > 0) {
                        pq.add(new Pair(waitingTime[i], i));
                    }
                }
            }
        }

        return sequence;
    }

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair pair = (Pair) obj;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
