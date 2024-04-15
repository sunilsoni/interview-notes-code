package com.interview.notes.code.months.april24.test6;

import java.util.ArrayList;
import java.util.List;

class Result3 {
    public static long makePowerNonDecreasing(List<Integer> power) {
        int n = power.size();
        long sum = 0;

        for (int i = 1; i < n; i++) {
            if (power.get(i) < power.get(i - 1)) {
                int diff = power.get(i - 1) - power.get(i);
                power.set(i, power.get(i - 1));
                sum += diff;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        // Example test case 1
        List<Integer> power1 = new ArrayList<>();
        power1.add(3);
        power1.add(2);
        power1.add(1);
        System.out.println(makePowerNonDecreasing(power1)); // Output should be 2

        // Example test case 2
        List<Integer> power2 = new ArrayList<>();
        power2.add(3);
        power2.add(5);
        power2.add(2);
        power2.add(3);
        System.out.println(makePowerNonDecreasing(power2)); // Output should be 3


        // Example test case 3
        List<Integer> power3 = new ArrayList<>();
        power3.add(4);
        power3.add(3);
        power3.add(5);
        power3.add(2);
        power3.add(3);
        System.out.println(makePowerNonDecreasing(power3)); // Output should be 3

        // Example test case4
        List<Integer> power4 = new ArrayList<>();
        power4.add(3);
        power4.add(3);
        power4.add(2);
        power4.add(1);
        System.out.println(makePowerNonDecreasing(power4)); // Output should be 2
        // Additional test cases
        // ... Add more test cases as needed
    }
}