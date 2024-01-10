package com.interview.notes.code.months.jan24.test2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int turn = 0;
        if (x == p) {
            System.out.println("Draw");
            return;
        }
        if (p < x) {
            turn = (p - 1) / m;
            p = (p - 1) % m + 1;
            x = (x - 1) / m;
        } else {
            turn = (n - p) / m;
            p = m - (n - p - 1) % m - 1;
            x = (x - 1) / m;
        }
        if (turn % 2 == x % 2) {
            System.out.println("Steve");
        } else {
            System.out.println("Harvey");
        }
    }
}
