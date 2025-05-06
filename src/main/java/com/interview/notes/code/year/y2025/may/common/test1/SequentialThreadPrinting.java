package com.interview.notes.code.year.y2025.may.common.test1;

public class SequentialThreadPrinting {
    private static final int MAX_NUMBER = 50;
    private static volatile int currentNumber = 1;
    private static final Object lock = new Object();

    static class NumberPrinter implements Runnable {
        private final int threadId;

        public NumberPrinter(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (currentNumber <= MAX_NUMBER) {
                synchronized (lock) {
                    if (currentNumber % 5 == threadId || 
                        (threadId == 0 && currentNumber % 5 == 5)) {
                        System.out.println("Thread " + (threadId + 1) + 
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
        // Create and start 5 threads
        for (int i = 0; i < 5; i++) {
            new Thread(new NumberPrinter(i), "Thread-" + (i + 1)).start();
        }
    }
}
