package com.interview.notes.code.test.test3;

class NumberPrinter implements Runnable {
    private static final Object lock = new Object();
    private static int number = 1;
    private final boolean isEven;

    public NumberPrinter(boolean isEven) {
        this.isEven = isEven;
    }

    @Override
    public void run() {
        while (number <= 10) {
            synchronized (lock) {
                if ((number % 2 == 0 && isEven) || (number % 2 != 0 && !isEven)) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    lock.notify(); // Notify the other thread waiting on the lock
                } else {
                    try {
                        lock.wait(); // Wait until it's the thread's turn to print
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NumberPrinter printer1 = new NumberPrinter(true); // For even numbers
        NumberPrinter printer2 = new NumberPrinter(false); // For odd numbers

        Thread t1 = new Thread(printer1, "t1");
        Thread t2 = new Thread(printer2, "t2");

        t1.start();
        t2.start();
    }
}
