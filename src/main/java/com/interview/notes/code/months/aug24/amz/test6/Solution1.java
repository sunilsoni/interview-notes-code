package com.interview.notes.code.months.aug24.amz.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution1 {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        if (n == 0) return 0;

        // `minPositiveCumulativeSum` will track the minimum cumulative sum after making certain values negative
        int cumulativeSum = 0;
        int minPositiveCumulativeSum = Integer.MAX_VALUE; // This is key to maintain all cumulative positive
        int countNegatives = 0;

        for (int i = 0; i < n; i++) {
            cumulativeSum += PnL.get(i);

            if (cumulativeSum < minPositiveCumulativeSum) {
                minPositiveCumulativeSum = cumulativeSum;
            }

            if (minPositiveCumulativeSum > 0) {
                countNegatives = i + 1; // We can have all i negatives because all previous sums are positive
            }
        }

        return countNegatives;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Integer> PnL = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int PnLItem = Integer.parseInt(scanner.nextLine().trim());
            PnL.add(PnLItem);
        }

        int result = getMaxNegativePnL(PnL);
        System.out.println(result);
    }
}