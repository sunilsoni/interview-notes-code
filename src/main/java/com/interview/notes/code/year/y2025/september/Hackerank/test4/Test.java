package com.interview.notes.code.year.y2025.september.Hackerank.test4;

public class Test {
    public Test() {
        Bar b = new Bar();
        Bar b1 = new Bar();
        update(b);   // prints 20
        update(b1);  // prints 20
        b1 = b;
        update(b);   // prints 20
        update(b1);  // prints 20
    }

    public static void main(String[] args) {
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
