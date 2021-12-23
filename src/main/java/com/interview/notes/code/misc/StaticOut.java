package com.interview.notes.code.misc;

class StaticOut {
    static int x;
    static int y;

    void add(int a, int b) {
        x = a + b;
        y = x + b;
    }
}

class StaticUse {
    public static void main(String args[]) {
        StaticOut obj1 = new StaticOut();
        StaticOut obj2 = new StaticOut();
        int a = 2;
        obj1.add(a, a + 1);
        obj2.add(5, a);
        System.out.println(obj1.x + " " + obj2.y);//7, 9
    }
}

