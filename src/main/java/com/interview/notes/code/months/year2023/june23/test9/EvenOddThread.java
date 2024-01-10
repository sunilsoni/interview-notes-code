package com.interview.notes.code.months.year2023.june23.test9;

public class EvenOddThread {
    private static final Object lock = new Object();
    private static int number = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printEvenNumbers());
        Thread t2 = new Thread(() -> printOddNumbers());

        t1.start();
        t2.start();
    }

    public static void printEvenNumbers() {
        while (number <= 10) {
            synchronized (lock) {
                if (number % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void printOddNumbers() {
        while (number <= 10) {
            synchronized (lock) {
                if (number % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
