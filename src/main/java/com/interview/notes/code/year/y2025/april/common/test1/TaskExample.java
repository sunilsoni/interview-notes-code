package com.interview.notes.code.year.y2025.april.common.test1;

class NumberPrinter extends Thread {
    private final int limit;

    public NumberPrinter(int limit) {
        this.limit = limit;
    }

    public void run() {
        for (int i = 1; i <= limit; i++) {
            System.out.println(i + " - " + Thread.currentThread().getName());
        }
    }
}

public class TaskExample {
    public static void main(String[] args) {
        NumberPrinter thread1 = new NumberPrinter(5);
        NumberPrinter thread2 = new NumberPrinter(3);

        thread1.start();
        thread2.start();
    }
}
