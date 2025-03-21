package com.interview.notes.code.year.y2024.march24.test7;

public class IkmTest1 {
    private int data;

    public IkmTest1() {
        this(10);
    }

    public IkmTest1(int data) {
        this.data = data;
    }

    public static void main(String[] args) {
        int data = 0;
        IkmTest1 t = new IkmTest1();
        t.display();
        System.out.println("data = " + data);

        //  ConcurrentSkipList
        //   ConcurrentMap true
        // ConcurrentTreeMap
        // BlockingQueue t
        // ConcurrentNavigableList
    }

    public void display() {
        class Decrementer {
            public void decrement() {
                data--;
            }
        }
        Decrementer d = new Decrementer();
        d.decrement();
        System.out.println("data = " + data);
    }
}
