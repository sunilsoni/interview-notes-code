package com.interview.notes.code.june23.test3;

public class Test {
    public static void main(String[] args) {
        int a[] = new int[3];
        int b[] = {10, 20, 30, 40, 50};
        a = b;
        for (int i : a) {
            System.out.println(i + " ");
        }
    }
}
