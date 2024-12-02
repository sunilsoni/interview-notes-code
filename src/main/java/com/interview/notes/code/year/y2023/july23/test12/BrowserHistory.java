package com.interview.notes.code.year.y2023.july23.test12;

import java.util.Stack;

class BrowserHistory {

    private String homepage;
    private Stack<String> history;
    private Stack<String> forward;

    public BrowserHistory(String homepage) {
        this.homepage = homepage;
        this.history = new Stack<>();
        this.forward = new Stack<>();
        this.history.push(homepage);
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        System.out.println(browserHistory.back(0)); // null, since the browser is currently on the homepage
        browserHistory.visit("google.com");
        System.out.println(browserHistory.back(0)); // null, since the browser is currently on "google.com"
        browserHistory.visit("facebook.com");
        System.out.println(browserHistory.back(1)); // "google.com"
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(2)); // "facebook.com"
        System.out.println(browserHistory.forward(1)); // "youtube.com"
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2)); // null, since the browser is currently on "linkedin.com"
        System.out.println(browserHistory.back(2)); // "facebook.com"
        System.out.println(browserHistory.back(7)); // "leetcode.com"
    }

    public void visit(String url) {
        history.push(url);
        forward.clear();
    }

    public String back(int steps) {
        if (steps > history.size()) {
            steps = history.size();
        }
        for (int i = 0; i < steps; i++) {
            forward.push(history.pop());
        }
        return history.peek();
    }

    public String forward(int steps) {
        if (steps > forward.size()) {
            steps = forward.size();
        }
        for (int i = 0; i < steps; i++) {
            history.push(forward.pop());
        }
        return history.peek();
    }
}
