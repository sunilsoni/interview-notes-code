package com.interview.notes.code.year.y2024.aug24.amz.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainYou {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        int totalPositive = 0;
        int totalNegative = 0;

        // Calculate total positive and negative values
        for (int i = 0; i < n; i++) {
            if (PnL.get(i) > 0) {
                totalPositive += PnL.get(i);
            } else {
                totalNegative += PnL.get(i);
            }
        }

        // The maximum number of negatives we can afford
        int maxNegatives = 0;
        int cumulativeSum = 0;

        // Sort the PnL array to handle the largest losses first
        PnL.sort((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));

        for (int i = 0; i < n; i++) {
            cumulativeSum += PnL.get(i);
            if (cumulativeSum <= 0) {
                break;
            }
            if (PnL.get(i) < 0) {
                maxNegatives++;
            }
        }

        return maxNegatives;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of elements
        int n = scanner.nextInt();
        List<Integer> PnL = new ArrayList<>();

        // Read PnL values
        for (int i = 0; i < n; i++) {
            PnL.add(scanner.nextInt());
        }

        // Get the maximum number of negative PnL
        int result = getMaxNegativePnL(PnL);
        System.out.println(result);

        // Close the scanner
        scanner.close();
    }
}