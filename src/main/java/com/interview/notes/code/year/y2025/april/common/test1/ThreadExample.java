package com.interview.notes.code.year.y2025.april.common.test1;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from MyThread! Running: " + Thread.currentThread().getName());
        // throws RuntimeException();
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start(); // Starts the thread

        MyThread thread2 = new MyThread();
        thread2.start(); // Starts another thread
    }
}
