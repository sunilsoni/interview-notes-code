package com.interview.notes.code.months.Oct23.test5;

import java.util.ArrayList;
import java.util.List;

public class BrowserHistory1 {
    // List to maintain the browser history
    private List<String> history;
    // Integer to keep track of the current index
    private int currentIndex;

    // Initialize with the homepage
    public BrowserHistory1(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
    }

    // Visit a new URL
    public void visit(String url) {
        // Remove forward history
        while (history.size() > currentIndex + 1) {
            history.remove(history.size() - 1);
        }
        // Add the new URL and update the current index
        history.add(url);
        currentIndex++;
    }

    // Move back 'steps' steps
    public String back(int steps) {
        // Calculate the new index after moving back
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }

    // Move forward 'steps' steps
    public String forward(int steps) {
        // Calculate the new index after moving forward
        currentIndex = Math.min(history.size() - 1, currentIndex + steps);
        return history.get(currentIndex);
    }
}
