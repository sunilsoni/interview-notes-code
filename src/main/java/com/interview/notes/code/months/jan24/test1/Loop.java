package com.interview.notes.code.months.jan24.test1;

public class Loop {
    public static void main(String[] args) {
        int i, j;
        int n = 5;
        for (i = 0; i < n; i++) {
            for (j = n - i; j > 0; j--) {
                System.out.print("+");
            }
            for (j = 0; j <= i; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
