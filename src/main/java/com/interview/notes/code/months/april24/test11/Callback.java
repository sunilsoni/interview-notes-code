package com.interview.notes.code.months.april24.test11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Interface for defining callback functions
public interface Callback {
    void run(); // Define a method to be implemented by callback functions
}

// Class representing an event and its handling mechanism
class Event {
    // List to store registered callbacks
    private final List<Callback> callbacks = new ArrayList<>();
    // Executor service for executing callbacks asynchronously
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    // Lock for ensuring thread safety when accessing shared resources
    private final Lock lock = new ReentrantLock();
    // Flag to indicate whether the event has occurred
    private volatile boolean eventHasOccurred = false;

    // Method to register a callback
    public void reg_cb(Callback cb) {
        // Check if the event has already occurred
        if (eventHasOccurred) {
            // If yes, submit the callback for immediate execution asynchronously
            executorService.submit(() -> cb.run());
            return;
        }

        // Acquire the lock to ensure thread safety
        lock.lock();
        try {
            // Add the callback to the list of callbacks
            callbacks.add(cb);
        } finally {
            // Release the lock to allow other threads to access shared resources
            lock.unlock();
        }
    }

    // Method to trigger the event
    public void eventFired() {
        List<Callback> callbacksToExecute;
        // Acquire the lock to ensure thread safety
        lock.lock();
        try {
            // Check if the event has already occurred
            if (eventHasOccurred) {
                // If yes, return without processing further
                return;
            }
            // Set the event flag to indicate that the event has occurred
            eventHasOccurred = true;
            // Create a copy of the list of callbacks to execute
            callbacksToExecute = new ArrayList<>(callbacks);
            // Clear the original list of callbacks
            callbacks.clear();
        } finally {
            // Release the lock to allow other threads to access shared resources
            lock.unlock();
        }

        // Execute callbacks asynchronously outside the lock to minimize lock duration
        for (Callback cb : callbacksToExecute) {
            executorService.submit(() -> cb.run());
        }
    }

    // Method to shut down the executor service
    public void shutdown() {
        executorService.shutdownNow();
    }
}
