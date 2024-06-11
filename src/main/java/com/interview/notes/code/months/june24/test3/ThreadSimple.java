package com.interview.notes.code.months.june24.test3;

public class ThreadSimple extends Thread {
    public ThreadSimple(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println("New thread is working");
        try {
            sleep(5000);  // Sleep for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new ThreadSimple("Test 1");
        thread.start();  // Launch the thread for execution
    }
}
