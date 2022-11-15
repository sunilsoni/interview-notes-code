package com.interview.notes.code.thread;

/* OddEvenThreadType1: One thread prints odd, other thread prints even so o/p will be 0 2 4 6 8 10 1 3 5 7 9 11
 */
public class OddEvenThreadType1 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable1();
        Runnable r2 = new Runnable2();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}

class Runnable1 implements Runnable {
    public void run() {
        for (int i = 1; i <= 11; i += 2) {
            System.out.println(i);
        }
    }
}

class Runnable2 implements Runnable {
    public void run() {
        for (int i = 0; i < 11; i += 2) {
            System.out.println(i);
        }
    }
}