package com.interview.notes.code.months.jan24.test13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private int count;
    private Lock lock;

    public SharedResource() {
        count = 0;
        lock = new ReentrantLock();
    }

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

class WorkerThread extends Thread {
    private SharedResource sharedResource;

    public WorkerThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        // Perform concurrent task
        sharedResource.increment();
    }
}

public class MultithreadedComponent {
    public static void main(String[] args) {
        final int NUM_THREADS = 5;
        SharedResource sharedResource = new SharedResource();

        // Create and start worker threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new WorkerThread(sharedResource);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display the final count
        System.out.println("Final count: " + sharedResource.getCount());
    }
}
