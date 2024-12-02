package com.interview.notes.code.year.y2023.nov23.hackerearth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Crossroad1 {
    public static void main(String[] args) {

        int N = 3;
        int[][] A = {
                {1, 0, 1, 1},
                {2, 1, 0, 0},
                {3, 0, 0, 5}
        };

        //List<List<Integer>> result = Crossroad.solve2(N, A);
        int[][] result = Crossroad1.solve2(N, A);

     /*   for(List<Integer> item : result) {
            System.out.println(item.get(0) + " " + item.get(1));
        }*/
    }

    public static int[][] solve1(int N, int[][] A) {

        PriorityQueue<Road> queue = new PriorityQueue<>(
                (a, b) -> a.waitingTime - b.waitingTime);

        for (int i = 0; i < N; i++) {
            int vehicles = A[i][1] + A[i][2] + A[i][3];
            queue.offer(new Road(i + 1, vehicles, 0));
        }

        int[][] result = new int[N][2];
        int time = 0;
        int index = 0;

        while (!queue.isEmpty()) {

            Road road = queue.poll();

            result[index][0] = road.number;
            result[index][1] = time;
            index++;

            time += road.vehicles;

            for (Road r : queue) {
                r.waitingTime += road.vehicles;
            }
        }

        return result;
    }

    public static int[][] solve2(int N, int[][] A) {

        int[] vehicles = new int[N];
        int[] order = new int[N];

        for (int i = 0; i < N; i++) {
            vehicles[i] = A[i][1] + A[i][2] + A[i][3];
        }

        boolean[] visited = new boolean[N];

        solveUtil(N, vehicles, order, visited, 0, 0);

        int[][] result = new int[N][2];

        for (int i = 0; i < N; i++) {
            result[i][0] = order[i] + 1;
            result[i][1] = i;
        }

        return result;

    }

    public static List<List<Integer>> solve(int N, int[][] A) {
        PriorityQueue<Road> queue = new PriorityQueue<>(
                (a, b) -> a.waitingTime - b.waitingTime);

        for (int i = 0; i < N; i++) {
            int vehicles = A[i][1] + A[i][2] + A[i][3];
            queue.offer(new Road(i + 1, vehicles, 0));
        }

        List<List<Integer>> result = new ArrayList<>();
        int time = 0;

        while (!queue.isEmpty()) {
            Road road = queue.poll();
            result.add(Arrays.asList(road.number, time));

            time += road.vehicles;

            for (Road r : queue)
                r.waitingTime += road.vehicles;
        }

        return result;
    }

    static void solveUtil(int N, int[] vehicles, int[] order, boolean[] visited, int idx, int time) {

        if (idx == N)
            return;

        int minWait = Integer.MAX_VALUE;
        int nextRoad = -1;

        for (int i = 0; i < N; i++) {

            if (visited[i])
                continue;

            int wait = 0;

            for (int j = 0; j < N; j++) {
                if (!visited[j])
                    wait += vehicles[j];
            }

            if (wait < minWait) {
                minWait = wait;
                nextRoad = i;
            }
        }

        order[idx] = nextRoad;
        visited[nextRoad] = true;

        solveUtil(N, vehicles, order, visited, idx + 1, time + vehicles[nextRoad]);

    }

    static class Road {
        int number;
        int vehicles;
        int waitingTime;

        Road(int n, int v, int w) {
            this.number = n;
            this.vehicles = v;
            this.waitingTime = w;
        }
    }
}