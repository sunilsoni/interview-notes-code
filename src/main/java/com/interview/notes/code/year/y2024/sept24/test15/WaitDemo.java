package com.interview.notes.code.year.y2024.sept24.test15;

class GunFight {
    private int bullets = 40;

    synchronized public void fire(int bulletsToBeFired) {
        for (int i = 1; i <= bulletsToBeFired; i++) {
            if (bullets == 0) {
                System.out.println((i - 1) + " bullets fired and " + bullets + " remains");
                System.out.println("Invoking the wait() method");

                try {
                    wait();  // Thread waits until bullets are reloaded
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Continuing the fire after reloading");
            }

            bullets--;
            System.out.println((i) + " bullets fired and " + bullets + " remains");
        }

        System.out.println("The firing process is complete");
    }

    synchronized public void reload() {
        System.out.println("Reloading the magazine and resuming the thread using notify()");
        bullets += 40;  // Reload the bullets
        notify();  // Notify the waiting thread to continue firing
    }
}

public class WaitDemo extends Thread {
    public static void main(String[] args) {
        GunFight gf = new GunFight();

        // Fire thread
        new Thread(() -> {
            gf.fire(60);  // Fire more bullets than available
        }).start();

        // Reload thread with a delay
        new Thread(() -> {
            try {
                Thread.sleep(5000);  // Introduce a delay to simulate real reloading time
                gf.reload();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
