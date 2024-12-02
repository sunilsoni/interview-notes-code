package com.interview.notes.code.year.y2024.may24.test9;

public class Test {
    public Test() {
        Bar b = new Bar();
        Bar b1 = new Bar();
        update(b);
        update(b1);
        b1 = b;
        update(b);
        update(b1);
    }

    public static void main(String args[]) {
        new Test();
    }

    private void update(Bar bar) {
        bar.x = 20;
        System.out.println(bar.x);
    }

    private class Bar {
        int x = 10;
    }
}
