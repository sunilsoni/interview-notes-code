package com.interview.notes.code.year.y2023.nov23.hackerearth;

class hacker {

    public static int func(int[] no) {
        int b = no[0];
        for (int i : no) {
            if (i > b)
                b = i;
        }
        return b;
    }


    public static void main(String[] args) {
        int[] a = {128,
                132,
                98,
                116,
                100};
        int x = func(a);
        System.out.println(x);
    }
}