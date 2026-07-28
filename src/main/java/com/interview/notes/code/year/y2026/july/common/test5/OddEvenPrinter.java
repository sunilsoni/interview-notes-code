package com.interview.notes.code.year.y2026.july.common.test5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter {
    private final int MAX = 10;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();
    private int count = 1;

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();

        Thread t1 = new Thread(printer::printOdd, "Odd-Thread");
        Thread t2 = new Thread(printer::printEven, "Even-Thread");

        t1.start();
        t2.start();
    }

    public void printOdd() {
        while (count < MAX) {
            lock.lock();
            try {
                while (count % 2 == 0) {
                    cond.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + count++);
                cond.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (count <= MAX) {
            lock.lock();
            try {
                while (count % 2 != 0) {
                    cond.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + count++);
                cond.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}