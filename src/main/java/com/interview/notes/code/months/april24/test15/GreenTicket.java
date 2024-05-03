package com.interview.notes.code.months.april24.test15;

public class GreenTicket {

    public static void main(String[] args) {
        // Test cases
        System.out.println(greenTicket(1, 2, 3)); // Output: 0
        System.out.println(greenTicket(2, 2, 2)); // Output: 20
        System.out.println(greenTicket(1, 1, 2)); // Output: 10
    }

    public static int greenTicket(int a, int b, int c) {
        if (a == b && b == c) {
            return 20;
        } else if (a == b || a == c || b == c) {
            return 10;
        } else {
            return 0;
        }
    }
}
