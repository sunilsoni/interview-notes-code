package com.interview.notes.code.year.y2025.april.common.test2;

class MyRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from MyRunnable! Running: " + Thread.currentThread().getName());
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable); // Passing MyRunnable to Thread
        thread.start(); // Start the thread
    }
}
