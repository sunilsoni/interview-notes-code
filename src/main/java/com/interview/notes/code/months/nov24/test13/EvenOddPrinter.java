package com.interview.notes.code.months.nov24.test13;

public class EvenOddPrinter {
    private static int counter = 1;

    public static void main(String[] args) {
        Object lock = new Object();

        // Thread for printing odd numbers
        Thread oddThread = new Thread(() -> {
            while (counter <= 10) {
                synchronized (lock) {
                    if (counter % 2 != 0) {
                        System.out.println("Odd: " + counter);
                        counter++;
                        lock.notify();  // Notify other thread
                    } else {
                        try {
                            lock.wait();  // Wait for the even thread to print
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        // Thread for printing even numbers
        Thread evenThread = new Thread(() -> {
            while (counter <= 10) {
                synchronized (lock) {
                    if (counter % 2 == 0) {
                        System.out.println("Even: " + counter);
                        counter++;
                        lock.notify();  // Notify other thread
                    } else {
                        try {
                            lock.wait();  // Wait for the odd thread to print
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        // Start both threads
        oddThread.start();
        evenThread.start();
    }
}
