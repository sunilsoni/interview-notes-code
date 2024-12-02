package com.interview.notes.code.year.y2023.Oct23.test3;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeClass {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void incrementCount() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
