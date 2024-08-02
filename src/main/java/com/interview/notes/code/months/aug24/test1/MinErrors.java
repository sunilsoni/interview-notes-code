package com.interview.notes.code.months.aug24.test1;

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
        char[] arr = errorString.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '!') {
                arr[i] = replacement;
            }
        }
        
        long count01 = 0;
        long count10 = 0;
        long result = 0;

        // Count subsequences 01 and 10
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == '1') {
                        count01++;
                    }
                }
            } else if (arr[i] == '1') {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == '0') {
                        count10++;
                    }
                }
            }
        }

        result = (x * count01 + y * count10) % MOD;
        return result;
    }
}
