package com.interview.notes.code.year.y2024.nov24.test13;

import java.util.concurrent.Semaphore;

public class EvenOddPrinterWithSemaphore {
    private static int counter = 1;

    public static void main(String[] args) {
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

        // Thread for printing odd numbers
        Thread oddThread = new Thread(() -> {
            while (counter <= 10) {
                try {
                    oddSemaphore.acquire();  // Acquire the semaphore for odd numbers
                    if (counter % 2 != 0) {
                        System.out.println("Odd: " + counter);
                        counter++;
                    }
                    evenSemaphore.release();  // Release semaphore for even thread
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Thread for printing even numbers
        Thread evenThread = new Thread(() -> {
            while (counter <= 10) {
                try {
                    evenSemaphore.acquire();  // Acquire the semaphore for even numbers
                    if (counter % 2 == 0) {
                        System.out.println("Even: " + counter);
                        counter++;
                    }
                    oddSemaphore.release();  // Release semaphore for odd thread
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start both threads
        oddThread.start();
        evenThread.start();
    }
}
