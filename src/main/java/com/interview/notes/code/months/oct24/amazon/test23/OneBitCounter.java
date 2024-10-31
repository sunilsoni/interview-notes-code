package com.interview.notes.code.months.oct24.amazon.test23;

import java.util.ArrayList;
import java.util.List;

public class OneBitCounter {

    public static List<Integer> getOneBits(int n) {
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int position = 1;

        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
                result.add(position);
            }
            n >>= 1;
            position++;
        }

        result.add(0, count);
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[] testCases = {161, 1, 255, 256, 1024, 1000000000};

        for (int testCase : testCases) {
            System.out.println("Testing for n = " + testCase);
            List<Integer> result = getOneBits(testCase);

            System.out.println("Result: " + result);

            // Verify the result
            boolean pass = verifyResult(testCase, result);
            System.out.println(pass ? "PASS" : "FAIL");
            System.out.println();
        }
    }

    private static boolean verifyResult(int n, List<Integer> result) {
        if (result.isEmpty() || result.get(0) != result.size() - 1) {
            return false;
        }

        int reconstructed = 0;
        for (int i = 1; i < result.size(); i++) {
            reconstructed |= (1 << (result.get(i) - 1));
        }

        return reconstructed == n;
    }
}