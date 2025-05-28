package com.interview.notes.code.year.y2025.may.ibm.test1;

public class SequencePrinter {
    // Lock object for synchronization
    private static final Object lock = new Object();
    // Shared counter to track current number
    private static int counter = 1;
    // Maximum number to print
    private static int maxNumber;

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Normal case (N=10)
        testSequence(10);

        // Test Case 2: Edge case (N=1)
        testSequence(1);

        // Test Case 3: Large number (N=100)
        testSequence(100);

        // Test Case 4: Edge case (N=0)
        testSequence(0);
    }

    // Test method to run sequence for different N values
    private static void testSequence(int n) {
        System.out.println("\nTesting sequence for N = " + n);
        System.out.println("------------------------");

        // Reset counter for new test
        counter = 1;
        maxNumber = n;

        if (n <= 0) {
            System.out.println("Invalid input: N should be positive");
            return;
        }

        // Create three threads
        Thread t1 = new Thread(new NumberPrinter(1));
        Thread t2 = new Thread(new NumberPrinter(2));
        Thread t3 = new Thread(new NumberPrinter(3));

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        try {
            // Wait for all threads to complete
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Thread class to print numbers
    static class NumberPrinter implements Runnable {
        private final int threadId;

        public NumberPrinter(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            // Continue until we reach maxNumber
            while (counter <= maxNumber) {
                synchronized (lock) {
                    // Check if it's this thread's turn to print
                    if ((counter % 3) == (threadId % 3)) {
                        // Print the number with thread info
                        System.out.printf("THREAD-%d : %d%n", threadId, counter);
                        counter++;
                        // Wake up all waiting threads
                        lock.notifyAll();
                    } else {
                        try {
                            // Wait for other threads to complete their turn
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
}
