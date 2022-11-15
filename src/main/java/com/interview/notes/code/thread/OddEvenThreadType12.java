package com.interview.notes.code.thread;

/* OddEvenThreadType12: One thread prints odd, other thread prints even so o/p will be 0 2 4 6 8 10 1 3 5 7 9 11
 */
public class OddEvenThreadType12 {
    public static void main(String[] args) {
        Runnable r1 = new Runnable12(true); // isOdd = true
        Runnable r2 = new Runnable12(false);// isOdd = false
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}

class Runnable12 implements Runnable {
    private boolean isOdd;

    Runnable12(boolean isOdd) {
        this.isOdd = isOdd;
    }

    public void run() {
        if (isOdd) {
            for (int i = 1; i <= 11; i += 2) {
                System.out.println(i);
            }
        } else {
            for (int i = 0; i < 11; i += 2) {
                System.out.println(i);
            }
        }
    }
}