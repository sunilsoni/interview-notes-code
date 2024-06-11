package com.interview.notes.code.months.june24.test3;

public class ThreadDemo extends Thread {
    public void run() {
        System.out.println("Before start method");
        this.stop();
        System.out.println("After stop method");
    }

    public static void main(String args[]) {
        ThreadDemo a = new ThreadDemo();
        a.start();
    }
}
