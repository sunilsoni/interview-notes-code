package com.interview.notes.code.year.y2023.nov23.hackerearth;

class hack_func {
    public static void main(String[] args) {
        int a = 30;
        int b = 45;
        hacking(a, b);
    }

    public static void hacking(int a, int b) {
        System.out.println("a = " + a + " b= " + b);
        int c = a;
        a = b;
        b = c;
    }
}