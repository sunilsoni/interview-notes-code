package com.interview.notes.code.months.march24.test15;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void a() {
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            counter++;
            // some business logic for method A
        }
        resetCounter();
    }

    public void b() {
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            counter++;
            // some business logic for method B
        }
        resetCounter();
    }

    private synchronized void resetCounter() {
        // Reset the counter to 0
        // This method is synchronized to ensure thread safety
        // counter = 0;
    }
}
