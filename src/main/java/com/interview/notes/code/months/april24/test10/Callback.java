package com.interview.notes.code.months.april24.test10;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface Callback {
    void run(); // The Callback interface with a single method that must be implemented by the listener.
}

class Event {
    // A thread-safe list to store callbacks. CopyOnWriteArrayList is used to avoid ConcurrentModificationException
    // during iteration when callbacks might be added or removed by other threads.
    private final List<Callback> callbacks = new CopyOnWriteArrayList<>();

    // A flag to indicate whether the event has occurred. It's marked as volatile to ensure the visibility of changes across threads.
    private volatile boolean eventHasOccurred = false;

    // An ExecutorService to manage asynchronous execution of callbacks.
    // Using a cached thread pool for efficient reuse of threads.
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public synchronized void reg_cb(Callback cb) {
        // Synchronization ensures that checking the state and registering the callback happens atomically,
        // preventing race conditions.
        if (eventHasOccurred) {
            // If the event has already occurred, submit the callback to the executor for immediate execution.
            executorService.submit(cb::run);
        } else {
            // If the event has not occurred yet, add the callback to the list for later execution.
            callbacks.add(cb);
        }
    }

    public synchronized void eventFired() {
        // Synchronization ensures that the event state change and callback execution are atomic operations.
        eventHasOccurred = true; // Set the event state to indicate the event has occurred.
        
        // Iterate over the registered callbacks and submit them for execution to the executor.
        // The use of a lambda expression within submit() allows for wrapping the callback execution
        // with additional behavior (like exception handling).
        callbacks.forEach(cb -> executorService.submit(() -> {
            try {
                cb.run(); // Try to run the callback.
            } catch (Exception e) {
                // Handle any exception thrown by the callback to prevent one callback's failure from affecting others.
                // This is where you could log the exception or take some corrective action.
            }
        }));
        
        // After running all callbacks, clear the list to ensure that each callback is only run once.
        callbacks.clear();
    }

    public synchronized void shutdown() {
        // A method to cleanly shut down the executor and stop accepting new tasks.
        // This should be called when the event system is no longer needed to free up system resources.
        executorService.shutdownNow();
    }
}
