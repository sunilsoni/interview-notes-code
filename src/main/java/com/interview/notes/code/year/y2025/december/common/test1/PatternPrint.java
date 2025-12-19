package com.interview.notes.code.year.y2025.december.common.test1;

public class PatternPrint {
    public static void main(String[] args) {
        for (int i = 6; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
