package com.interview.notes.code.year.y2023.june23.test11;

public class Main2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "x" + i + "=" + i * j + "    ");
            }
            System.out.println();
        }
    }

    public static void main1() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {
                int result = i * j;
                if (result < 10) {
                    System.out.print(j + "x" + i + "= " + result + "    ");
                } else {
                    System.out.print(j + "x" + i + "=" + result + "    ");
                }
            }
            System.out.println();
        }
    }
}
