package com.interview.notes.code.year.y2023.dec23.test1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PiParallelComputation extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;
    private int start;
    private int end;

    public PiParallelComputation(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int terms = 1000000;
        PiParallelComputation task = new PiParallelComputation(1, terms);
        double pi = pool.invoke(task);
        System.out.println("π computed in parallel: " + pi);
    }

    @Override
    protected Double compute() {
        if ((end - start) <= THRESHOLD) {
            double pi = 0.0;
            for (int i = start; i < end; i++) {
                pi += Math.pow(-1, i + 1) / (2 * i - 1);
            }
            return pi * 4;
        } else {
            int mid = start + (end - start) / 2;
            PiParallelComputation left = new PiParallelComputation(start, mid);
            PiParallelComputation right = new PiParallelComputation(mid, end);
            left.fork();
            double rightResult = right.compute();
            double leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
