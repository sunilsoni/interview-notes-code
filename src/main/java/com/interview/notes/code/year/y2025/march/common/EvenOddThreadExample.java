package com.interview.notes.code.year.y2025.march.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class EvenOddThreadExample {
    // Shared counter starting at 1
    private int counter = 1;
    private final int max;
    // Lock object for synchronization between threads
    private final Object lock = new Object();
    // Shared output list for testing purposes
    private final List<String> outputList;

    // Constructor takes the maximum number to print and a thread-safe list to record outputs
    public EvenOddThreadExample(int max, List<String> outputList) {
        this.max = max;
        this.outputList = outputList;
    }

    // Runnable for printing odd numbers
    class OddThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // If the counter exceeds max, exit loop
                    if (counter > max) {
                        lock.notifyAll();
                        break;
                    }
                    // Check if the number is odd
                    if (counter % 2 != 0) {
                        String message = Thread.currentThread().getName() + ": " + counter;
                        System.out.println(message);
                        outputList.add(message);
                        counter++;
                        lock.notifyAll();
                    } else {
                        // Wait for the other thread to print
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    // Runnable for printing even numbers
    class EvenThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (counter > max) {
                        lock.notifyAll();
                        break;
                    }
                    // Check if the number is even
                    if (counter % 2 == 0) {
                        String message = Thread.currentThread().getName() + ": " + counter;
                        System.out.println(message);
                        outputList.add(message);
                        counter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    // Start both threads to print the sequence
    public void startPrinting() throws InterruptedException {
        Thread oddThread = new Thread(new OddThread(), "OddThread");
        Thread evenThread = new Thread(new EvenThread(), "EvenThread");

        oddThread.start();
        evenThread.start();

        // Wait for both threads to finish
        oddThread.join();
        evenThread.join();
    }

    // Main method to run tests without JUnit
    public static void main(String[] args) throws InterruptedException {
        // Test Case 1: Standard case with max = 100
        List<String> output1 = Collections.synchronizedList(new ArrayList<>());
        EvenOddThreadExample printer1 = new EvenOddThreadExample(100, output1);
        printer1.startPrinting();
        boolean test1 = validateOutput(output1, 100);
        System.out.println("Test Case 1 (max=100): " + (test1 ? "PASS" : "FAIL"));

        // Test Case 2: Edge Case with max = 1
        List<String> output2 = Collections.synchronizedList(new ArrayList<>());
        EvenOddThreadExample printer2 = new EvenOddThreadExample(1, output2);
        printer2.startPrinting();
        boolean test2 = validateOutput(output2, 1);
        System.out.println("Test Case 2 (max=1): " + (test2 ? "PASS" : "FAIL"));

        // Test Case 3: Large Data input with max = 10000
        List<String> output3 = Collections.synchronizedList(new ArrayList<>());
        EvenOddThreadExample printer3 = new EvenOddThreadExample(10000, output3);
        printer3.startPrinting();
        boolean test3 = validateOutput(output3, 10000);
        System.out.println("Test Case 3 (max=10000): " + (test3 ? "PASS" : "FAIL"));
    }

    // Validate that the output list is as expected:
    // - It contains exactly 'max' entries.
    // - Each entry is of the form "OddThread: <odd number>" or "EvenThread: <even number>" in order.
    private static boolean validateOutput(List<String> output, int max) {
        if (output.size() != max) {
            return false;
        }
        // Generate expected output using a simple loop
        for (int i = 1; i <= max; i++) {
            String expectedThread = (i % 2 != 0) ? "OddThread" : "EvenThread";
            String expectedMessage = expectedThread + ": " + i;
            if (!expectedMessage.equals(output.get(i - 1))) {
                return false;
            }
        }
        return true;
    }
}