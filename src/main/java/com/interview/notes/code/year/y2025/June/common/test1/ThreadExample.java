package com.interview.notes.code.year.y2025.June.common.test1;

class SharedCounter {
    private int value = 0;

    public synchronized void add(int number) {
        value += number;
    }

    public int getValue() {
        return value;
    }
}

class OddNumberThread extends Thread {
    private final SharedCounter counter;

    public OddNumberThread(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i += 2) { // Adding odd numbers
            counter.add(i);
            System.out.println("Odd Thread Added: " + i);
        }
    }
}

class EvenNumberThread extends Thread {
    private final SharedCounter counter;

    public EvenNumberThread(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) { // Adding even numbers
            counter.add(i);
            System.out.println("Even Thread Added: " + i);
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();

        Thread oddThread = new OddNumberThread(counter);
        Thread evenThread = new EvenNumberThread(counter);

        oddThread.start();
        evenThread.start();

        oddThread.join(); // Wait for threads to finish
        evenThread.join();

        System.out.println("Final Value of Shared Variable: " + counter.getValue());
    }
}
