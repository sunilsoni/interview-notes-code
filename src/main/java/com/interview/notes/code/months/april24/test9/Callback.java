package com.interview.notes.code.months.april24.test9;

import java.util.ArrayList;
import java.util.List;

public interface Callback {
    void run();
}

class Event {
    private final List<Callback> callbacks = new ArrayList<>();
    private boolean eventHasOccurred = false;

    public synchronized void reg_cb(Callback cb) {
        if (eventHasOccurred) {
            // If the event has already occurred, run the callback immediately
            cb.run();
        } else {
            // Otherwise, add the callback to the list of callbacks to be called
            callbacks.add(cb);
        }
    }

    public synchronized void eventFired() {
        eventHasOccurred = true;
        // Run all registered callbacks
        for (Callback cb : callbacks) {
            cb.run();
        }
        // Clear the callbacks if they should only be called once
        callbacks.clear();
    }
}
