package com.interview.notes.code.months.nov23.test1;

import java.util.ArrayList;
import java.util.List;

public class PerfectNumberFinder {

    public static void main(String[] args) {
        PerfectNumberFinder finder = new PerfectNumberFinder();
        List<Integer> result = finder.findPerfectNumbers(1000);
        System.out.println("Perfect numbers up to 1000: " + result);
    }

    // Method to find perfect numbers up to a given limit
    public List<Integer> findPerfectNumbers(int upperLimit) {
        List<Integer> perfectNumbersList = new ArrayList<>();
        for (int i = 1; i <= upperLimit; i++) {
            int sum = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                perfectNumbersList.add(i);
            }
        }
        return perfectNumbersList;
    }
}
