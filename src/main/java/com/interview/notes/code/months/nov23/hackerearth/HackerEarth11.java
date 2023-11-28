package com.interview.notes.code.months.nov23.hackerearth;

class HackerEarth11 {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
        while (++a < 3) {
            int c = -a + b++;
            switch (b) {
                default:
                case 0:
                    a = ++a + a--;
                case 1:
                    a += a-- + ++b;
            }
        }
        System.out.println(a);
    }
}