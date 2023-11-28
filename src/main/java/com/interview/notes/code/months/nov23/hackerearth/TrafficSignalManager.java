package com.interview.notes.code.months.nov23.hackerearth;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TrafficSignalManager {
    public static int[][] solve1(int N, int[][] A) {
        PriorityQueue<Road> queue = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.waitingTime));

        for (int i = 0; i < N; i++) {
            int vehicles = A[i][1] + A[i][2] + A[i][3];
            queue.offer(new Road(A[i][0], vehicles, 0));
        }

        int[][] result = new int[N][2];
        int time = 0;
        int index = 0;

        while (!queue.isEmpty()) {
            Road road = queue.poll();
            result[index][0] = road.number;
            result[index][1] = time;
            index++;

            int clearTime = (road.vehicles + 2) / 3;
            time += clearTime;

            for (Road r : queue) {
                r.waitingTime += clearTime;
            }
        }

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

    static class Road {
        int number;
        int vehicles;
        int waitingTime;

        Road(int number, int vehicles, int waitingTime) {
            this.number = number;
            this.vehicles = vehicles;
            this.waitingTime = waitingTime;
        }
    }
}
