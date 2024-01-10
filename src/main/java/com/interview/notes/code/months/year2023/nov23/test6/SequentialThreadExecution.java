package com.interview.notes.code.months.year2023.nov23.test6;

public class SequentialThreadExecution {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task("t1"));
        Thread t2 = new Thread(new Task("t2"));
        Thread t3 = new Thread(new Task("t3"));

        try {
            t1.start();
            t1.join(); // Wait for t1 to finish

            t2.start();
            t2.join(); // Wait for t2 to finish

            t3.start();
            t3.join(); // Wait for t3 to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted");
        }
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " is running.");
        // Perform the task here
    }
}
