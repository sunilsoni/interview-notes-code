package com.interview.notes.code.misc.test6;

public class static_use {

    public static void main(String[] args) {


        static_out obj1 = new static_out();
        static_out obj2 = new static_out();
        int a = 2;
        obj1.add(a, a + 1);
        obj2.add(5, a);
        System.out.println(static_out.x + " " + static_out.y);

    }
}

class static_out {
    static int x;
    static int y;

    void add(int a, int b) {
        x = a + b;
        y = x + b;
    }
}