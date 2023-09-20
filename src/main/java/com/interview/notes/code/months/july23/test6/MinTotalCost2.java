package com.interview.notes.code.months.july23.test6;


import java.util.HashSet;
import java.util.PriorityQueue;

class Worker implements Comparable<Worker> {
    int cost;
    int index;

    public Worker(int cost, int index) {
        this.cost = cost;
        this.index = index;
    }

    @Override
    public int compareTo(Worker o) {
        if (this.cost != o.cost) {
            return this.cost - o.cost;
        } else {
            return this.index - o.index;
        }
    }
}

public class MinTotalCost2 {
    public static int minCostToHireWorkers(int[] costs, int k, int candidates) {
        PriorityQueue<Worker> minHeap = new PriorityQueue<>();

        int n = costs.length;
        for (int i = 0; i < candidates; i++) {
            minHeap.offer(new Worker(costs[i], i));
            minHeap.offer(new Worker(costs[n - i - 1], n - i - 1));
        }

        int totalCost = 0;
        HashSet<Integer> hired = new HashSet<>();
        while (hired.size() < k) {
            Worker worker = minHeap.poll();
            if (hired.contains(worker.index)) continue;
            totalCost += worker.cost;
            hired.add(worker.index);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int k = 3;
        int candidates = 4;
        System.out.println(minCostToHireWorkers(costs, k, candidates)); // Output: 11

        costs = new int[]{1, 2, 4, 1};
        k = 3;
        candidates = 3;
        System.out.println(minCostToHireWorkers(costs, k, candidates)); // Output: 4
    }
}
