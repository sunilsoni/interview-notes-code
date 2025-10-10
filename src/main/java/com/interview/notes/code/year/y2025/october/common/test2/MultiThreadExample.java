package com.interview.notes.code.year.y2025.october.common.test2;

public class MultiThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Worker(1);
        Thread t2 = new Worker(2);
        Thread t3 = new Worker(3);
        Thread t4 = new Worker(4);
        Thread t5 = new Worker(5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join(); t2.join(); t3.join(); t4.join(); t5.join();
        } catch (InterruptedException e) {}

        System.out.println("All threads finished!");
    }

    static class Worker extends Thread {
        private final int id;
        Worker(int id) { this.id = id; }
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread " + id + " - iteration " + i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        }
    }
}