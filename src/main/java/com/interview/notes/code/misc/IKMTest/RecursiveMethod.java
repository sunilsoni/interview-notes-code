package com.interview.notes.code.misc.IKMTest;

public class RecursiveMethod {
    public static int calculateSum(int n, int jumpValue) {
        if (n < 2) return 0;
        if (n == jumpValue) return jumpValue * calculateSum(jumpValue - 1, jumpValue);

        return n + calculateSum(n - 2, jumpValue);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(calculateSum(10, 4));
    }

}
