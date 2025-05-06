package com.interview.notes.code.year.y2025.may.common.test2;

public class SequentialThreadPrinting {
    private static final int MAX_NUMBER = 50;
    private static volatile int currentNumber = 1;
    private static final Object lock = new Object();

    static class NumberPrinter implements Runnable {
        private final int threadNumber; // 1 to 5 instead of 0 to 4

        public NumberPrinter(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            while (currentNumber <= MAX_NUMBER) {
                synchronized (lock) {
                    // Check if it's this thread's turn to print
                    if ((currentNumber - threadNumber) % 5 == 0) {
                        System.out.println("Thread " + threadNumber + 
                                         " prints: " + currentNumber);
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create and start 5 threads (numbered 1 to 5)
        for (int i = 1; i <= 5; i++) {
            new Thread(new NumberPrinter(i), "Thread-" + i).start();
        }
    }

    // Test method
    public static void testSequentialPrinting() {
        System.out.println("Starting test with 50 numbers...");
        main(null);
        
        // Wait for completion
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify final number
        if (currentNumber == MAX_NUMBER + 1) {
            System.out.println("\nTest PASSED: All numbers printed correctly");
        } else {
            System.out.println("\nTest FAILED: Not all numbers were printed");
        }
    }
}
