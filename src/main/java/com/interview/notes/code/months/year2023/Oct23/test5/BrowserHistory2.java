package com.interview.notes.code.months.year2023.Oct23.test5;

import java.util.ArrayList;

class BrowserHistory2 {
    // Use an ArrayList to keep track of the browser history.
    ArrayList<String> historyList = new ArrayList<>();
    // Pointers to keep track of the current and last indices.
    int currentIndex = 0;
    int lastIndex = 0;

    // Constructor
    public BrowserHistory2(String homepage) {
        // Initialize history with the homepage and set the current and last index to 0.
        historyList.add(homepage);
        currentIndex = 0;
        lastIndex = 0;
    }

    public static void main(String[] args) {
        // Initialize with homepage "leetcode.com"
        BrowserHistory2 browserHistory = new BrowserHistory2("leetcode.com");

        // Visit "google.com"
        browserHistory.visit("google.com");

        // Visit "facebook.com"
        browserHistory.visit("facebook.com");

        // Visit "youtube.com"
        browserHistory.visit("youtube.com");

        // Test Back
        System.out.println(browserHistory.back(1));  // Should print "facebook.com"

        // Test another Back
        System.out.println(browserHistory.back(1));  // Should print "google.com"

        // Test Forward
        System.out.println(browserHistory.forward(1)); // Should print "facebook.com"

        // Visit "linkedin.com"
        browserHistory.visit("linkedin.com");

        // Test Forward with no forward history
        System.out.println(browserHistory.forward(2));  // Should print "linkedin.com"

        // Test Back twice
        System.out.println(browserHistory.back(2));  // Should print "google.com"

        // Test Back more steps than available
        System.out.println(browserHistory.back(7));  // Should print "leetcode.com"
    }

    // Visit a new URL
    public void visit(String url) {
        // Remove forward history if any
        while (historyList.size() > currentIndex + 1) {
            historyList.remove(historyList.size() - 1);
        }
        // Add the new URL to the history
        historyList.add(url);
        // Update current and last index
        currentIndex++;
        lastIndex = currentIndex;
    }

    // Move back in history
    public String back(int steps) {
        // Update the current index
        currentIndex = Math.max(0, currentIndex - steps);
        // Return the URL at the current index
        return historyList.get(currentIndex);
    }

    // Move forward in history
    public String forward(int steps) {
        // Update the current index
        currentIndex = Math.min(lastIndex, currentIndex + steps);
        // Return the URL at the current index
        return historyList.get(currentIndex);
    }
}


