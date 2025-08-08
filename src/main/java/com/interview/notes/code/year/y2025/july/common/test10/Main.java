package com.interview.notes.code.year.y2025.july.common.test10;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<Long> cubeSumFuture = executor.submit(() -> calculateSum(arr, 3));
        Future<Long> fourthPowerSumFuture = executor.submit(() -> calculateSum(arr, 4));
        Future<Long> fifthPowerSumFuture = executor.submit(() -> calculateSum(arr, 5));

        long cubeSum = cubeSumFuture.get();
        long fourthPowerSum = fourthPowerSumFuture.get();
        long fifthPowerSum = fifthPowerSumFuture.get();

        executor.shutdown();

        System.out.println(cubeSum + " " + fourthPowerSum + " " + fifthPowerSum);
    }

    private static long calculateSum(int[] arr, int power) {
        return Arrays.stream(arr)
                .mapToLong(n -> (long) Math.pow(n, power))
                .sum();
    }
}