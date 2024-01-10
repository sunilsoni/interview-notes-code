package com.interview.notes.code.months.year2023.nov23.hackerearth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TrafficSignalManager2 {
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

        int N = 3;
        int[][] A = {
                {1, 0, 1, 1},
                {2, 1, 0, 0},
                {3, 0, 0, 5}
        };

        List<List<Integer>> result = Crossroad1.solve(N, A);
        //int[][] result = Crossroad.solve(N, A);

        for (List<Integer> item : result) {
            System.out.println(item.get(0) + " " + item.get(1));
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
