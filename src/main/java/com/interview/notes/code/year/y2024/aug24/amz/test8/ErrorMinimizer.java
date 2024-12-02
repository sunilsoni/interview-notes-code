package com.interview.notes.code.year.y2024.aug24.amz.test8;

public class ErrorMinimizer {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int countZero = 0;
        int countOne = 0;
        int countExclamation = 0;

        // Count occurrences of '0', '1', and '!'
        for (char ch : errorString.toCharArray()) {
            if (ch == '0') {
                countZero++;
            } else if (ch == '1') {
                countOne++;
            } else if (ch == '!') {
                countExclamation++;
            }
        }

        // Case when all '!' are replaced with '0'
        long errorsAllZero = (long) countOne * countZero % MOD * x % MOD;

        // Case when all '!' are replaced with '1'
        long errorsAllOne = (long) countZero * countOne % MOD * y % MOD;

        // Calculate total errors for both cases
        long totalErrorsZero = (errorsAllZero + errorsAllOne) % MOD;

        // Case when some '!' are '0' and some are '1'
        // If there are '?'s, the combined errors need to be recalculated
        long minErrors = Math.min(totalErrorsZero, (countExclamation > 0)
                ? Math.min((long) (countOne + countExclamation) * countZero % MOD * x % MOD,
                (long) (countZero + countExclamation) * countOne % MOD * y % MOD)
                : totalErrorsZero);

        return (int) minErrors;
    }


    public static void main(String[] args) {
        // Test cases as provided in the screenshots "01!0"
        System.out.println(getMinErrors("01!0", 2, 3)); // Output: 8
        System.out.println(getMinErrors("010!", 2, 3)); // Output: 8
        System.out.println(getMinErrors("!!!!!!", 23, 47)); // Output: 0
    }
}