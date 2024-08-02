package com.interview.notes.code.months.aug24.test4;

public class MinErrors {

    public static void main(String[] args) {
        // Example tests
        System.out.println(getMinErrors("010!", 2, 3)); // Expected output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected output: 0
        System.out.println(getMinErrors("1011!1", 2, 3)); // Expected output: 9
    }

    public static int getMinErrors(String errorString, int x, int y) {
        long MOD = 1000000007;
        long errorsIfZero = calculateErrors(errorString, x, y, '0', MOD);
        long errorsIfOne = calculateErrors(errorString, x, y, '1', MOD);
        return (int) Math.min(errorsIfZero, errorsIfOne);
    }

    private static long calculateErrors(String errorString, int x, int y, char replacement, long MOD) {
        int n = errorString.length();
        long countOnes = 0;
        long count01 = 0;
        long count10 = 0;

        for (int i = 0; i < n; i++) {
            char c = errorString.charAt(i);
            if (c == '1' || (c == '!' && replacement == '1')) {
                count01 += i - countOnes;
                countOnes++;
            } else {
                count10 += countOnes;
            }
        }

        return (x * count01 + y * count10) % MOD;
    }
}