package com.interview.notes.code.months.april24.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Result1 {
    public static long makePowerNonDecreasing(List<Integer> power) {
        long sum = 0;
        for (int i = 1; i < power.size(); i++) {
            if (power.get(i) < power.get(i - 1)) {
                sum += (long) (power.get(i - 1) - power.get(i));
                power.set(i, power.get(i - 1));
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> power = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            power.add(scanner.nextInt());
        }
        scanner.close();

        System.out.println(makePowerNonDecreasing(power));
    }
}
