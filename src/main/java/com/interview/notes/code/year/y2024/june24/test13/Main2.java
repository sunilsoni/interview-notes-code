package com.interview.notes.code.year.y2024.june24.test13;

public class Main2 {
    public static void main(String[] args) {
        Main2 main = new Main2();
        main.runWithRunner();
    }

    private void runWithRunner() {
        Runner runner = new Runner();
        Thread thread = new Thread(runner);

        thread.start();
        System.out.println("Thread started");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended");
    }
}