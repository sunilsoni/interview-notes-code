package com.interview.notes.code.months.aug24.test5;

public class MinErrors {
    private static final long MOD = 1000000007;

    public static void main(String[] args) {
        // Example tests
        System.out.println(getMinErrors("010!", 2, 3)); // Expected output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected output: 0
        System.out.println(getMinErrors("1011!1", 2, 3)); // Expected output: 9
    }

    public static int getMinErrors(String errorString, int x, int y) {
        long errorsIfZero = calculateErrors(errorString, x, y, '0');
        long errorsIfOne = calculateErrors(errorString, x, y, '1');
        return (int) Math.min(errorsIfZero, errorsIfOne);
    }

    private static long calculateErrors(String errorString, int x, int y, char replacement) {
        long countOnes = 0;
        long countZeros = 0;
        long count01 = 0;
        long count10 = 0;

        for (int i = 0; i < errorString.length(); i++) {
            char c = errorString.charAt(i);
            if (c == '1' || (c == '!' && replacement == '1')) {
                count01 = (count01 + countZeros) % MOD;
                countOnes++;
            } else if (c == '0' || (c == '!' && replacement == '0')) {
                count10 = (count10 + countOnes) % MOD;
                countZeros++;
            }
        }

        long result = (x * count01 + y * count10) % MOD;
        return result;
    }
}