package com.interview.notes.code.months.year2023.nov23.hackerearth;

import java.util.*;

public class TrafficSignalManager1 {
    public static List<int[]> solve(int N, int[][] A) {
        PriorityQueue<Road> roadQueue = new PriorityQueue<>();
        for (int[] roadInfo : A) {
            roadQueue.add(new Road(roadInfo[0], roadInfo[1], roadInfo[2], roadInfo[3]));
        }

        List<int[]> result = new ArrayList<>();
        int currentTime = 0;
        while (!roadQueue.isEmpty()) {
            Road road = roadQueue.poll();
            result.add(new int[]{road.id, currentTime});
            currentTime += road.clearTime;
        }

        result.sort(Comparator.comparingInt(a -> a[0]));
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int N = scanner.nextInt();
            int[][] A = new int[N][4];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 4; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }

            List<int[]> result = solve(N, A);
            for (int[] res : result) {
                System.out.println(res[0] + " " + res[1]);
            }
        }
    }

    static class Road implements Comparable<Road> {
        int id, clearTime, waitTime;

        Road(int id, int twoWheelers, int threeWheelers, int fourWheelers) {
            this.id = id;
            this.clearTime = calculateClearTime(twoWheelers, threeWheelers, fourWheelers);
        }

        private int calculateClearTime(int twoWheelers, int threeWheelers, int fourWheelers) {
            int time = 0;
            while (twoWheelers >= 3) {
                twoWheelers -= 3;
                time++;
            }
            if (twoWheelers + fourWheelers + threeWheelers > 0) time++;
            return time;
        }

        @Override
        public int compareTo(Road other) {
            int compareClearTime = Integer.compare(this.clearTime, other.clearTime);
            if (compareClearTime == 0) {
                return Integer.compare(this.id, other.id);
            }
            return compareClearTime;
        }
    }
}
