package com.interview.notes.code.year.y2025.June.glider.test1;

public class Test {
    public Test() {
        Bar b = new Bar();
        Bar b1 = new Bar();
        update(b);     // 20
        update(b1);    // 20
        b1 = b;
        update(b);     // 20
        update(b1);    // 20
    }

    private void update(Bar bar) {
        bar.x = 20;
        System.out.println(bar.x);
    }

    public static void main(String[] args) {
        new Test();
    }

    private class Bar {
        int x = 10;
    }
}
