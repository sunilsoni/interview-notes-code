package com.interview.notes.code.months.april24.test12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface Callback {
    void run();
}

class Event {
    private final List<Callback> callbacks = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final Lock lock = new ReentrantLock();
    private volatile boolean eventHasOccurred = false;

    public void reg_cb(Callback cb) {
        if (eventHasOccurred) {
            executorService.submit(() -> cb.run());
            return;
        }

        lock.lock();
        try {
            callbacks.add(cb);
        } finally {
            lock.unlock();
        }
    }

    public void eventFired() {
        if (eventHasOccurred) {
            return; // Event already occurred, no need to process further
        }

        List<Callback> callbacksToExecute;
        lock.lock();
        try {
            if (eventHasOccurred) {
                return; // Check again in case event occurred while acquiring lock
            }
            eventHasOccurred = true; // Set event flag to true
            callbacksToExecute = new ArrayList<>(callbacks);
            callbacks.clear(); // Clear callbacks after execution
        } finally {
            lock.unlock();
        }

        for (Callback cb : callbacksToExecute) {
            executorService.submit(() -> cb.run());
        }
    }

    public void shutdown() {
        executorService.shutdownNow();
    }
}
