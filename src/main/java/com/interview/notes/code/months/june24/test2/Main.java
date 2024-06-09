package com.interview.notes.code.months.june24.test2;

class Counter implements Runnable {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    @Override
    public void run() {
        this.increment();
    }

    public int getValue() {
        return c;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Value of c: " + counter.getValue());
    }
}
