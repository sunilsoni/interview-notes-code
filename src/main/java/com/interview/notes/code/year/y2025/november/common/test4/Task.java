package com.interview.notes.code.year.y2025.november.common.test4;

class Task implements Runnable {
    private final Object lock1;
    private final Object lock2;

    Task(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        // T1 and T2 use same Task logic, but lock order is now consistent internally
        new Thread(new Task(lockA, lockB), "T1").start();
        new Thread(new Task(lockB, lockA), "T2").start();
    }

    public void run() {
        // Determine consistent lock order using identityHashCode
        Object firstLock = System.identityHashCode(lock1) < System.identityHashCode(lock2) ? lock1 : lock2;
        Object secondLock = (firstLock == lock1) ? lock2 : lock1;

        // Always acquire locks in the same order to prevent deadlock
        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " acquired first lock");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " acquired second lock");
            }
        }
    }
}
