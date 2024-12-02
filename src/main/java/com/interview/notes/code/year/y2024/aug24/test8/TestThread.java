package com.interview.notes.code.year.y2024.aug24.test8;

class TestThread {
    public static void main(String args[]) {
        threadDemo T1 = new threadDemo("T 1");
        T1.start();
        threadDemo T2 = new threadDemo("T 2");
        T2.start();
    }
}

class threadDemo implements Runnable {
    private Thread t;
    private String tName;

    threadDemo(String name) {
        tName = name;
    }

    public void run() {
        try {
            for (int i = 2; i > 0; i--) {
                System.out.println(tName + "," + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + tName + " interrupted.");
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, tName);
            t.start();
        }
    }
}
