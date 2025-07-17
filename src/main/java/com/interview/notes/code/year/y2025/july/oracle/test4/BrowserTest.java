package com.interview.notes.code.year.y2025.july.oracle.test4;

import java.util.Stack;

class BrowserPage {
    private String url;
    private String title;
    private long timestamp;

    public BrowserPage(String url, String title) {
        this.url = url;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Page[url=" + url + ", title=" + title + "]";
    }
}

class BrowserHistory {
    private Stack<BrowserPage> backStack;
    private Stack<BrowserPage> forwardStack;
    private BrowserPage currentPage;

    public BrowserHistory() {
        backStack = new Stack<>();
        forwardStack = new Stack<>();
    }

    public void visitPage(String url, String title) {
        if (currentPage != null) {
            backStack.push(currentPage);
        }
        forwardStack.clear(); // Clear forward history when visiting new page
        currentPage = new BrowserPage(url, title);
    }

    public BrowserPage back() {
        if (backStack.isEmpty()) {
            return currentPage; // Can't go back
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        return currentPage;
    }

    public BrowserPage forward() {
        if (forwardStack.isEmpty()) {
            return currentPage; // Can't go forward
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        return currentPage;
    }

    public BrowserPage getCurrentPage() {
        return currentPage;
    }
}

// Main class with test cases
public class BrowserTest {
    public static void main(String[] args) {
        testBasicNavigation();
        testEdgeCases();
        testLargeDataSet();
    }

    private static void testBasicNavigation() {
        System.out.println("Testing basic navigation...");
        BrowserHistory browser = new BrowserHistory();
        
        browser.visitPage("google.com", "Google");
        browser.visitPage("facebook.com", "Facebook");
        browser.visitPage("twitter.com", "Twitter");

        // Test back navigation
        assert browser.back().toString().contains("facebook.com");
        assert browser.back().toString().contains("google.com");

        // Test forward navigation
        assert browser.forward().toString().contains("facebook.com");
        assert browser.forward().toString().contains("twitter.com");

        System.out.println("Basic navigation test: PASSED");
    }

    private static void testEdgeCases() {
        System.out.println("\nTesting edge cases...");
        BrowserHistory browser = new BrowserHistory();

        // Test empty history
        assert browser.back() == null;
        assert browser.forward() == null;

        browser.visitPage("page1.com", "Page 1");
        // Test single page navigation
        assert browser.back() == browser.getCurrentPage();
        
        System.out.println("Edge cases test: PASSED");
    }

    private static void testLargeDataSet() {
        System.out.println("\nTesting large data set...");
        BrowserHistory browser = new BrowserHistory();

        // Simulate browsing 1000 pages
        for (int i = 0; i < 1000; i++) {
            browser.visitPage("page" + i + ".com", "Page " + i);
        }

        // Test navigation through large history
        for (int i = 0; i < 500; i++) {
            browser.back();
        }
        for (int i = 0; i < 250; i++) {
            browser.forward();
        }

        System.out.println("Large data set test: PASSED");
    }
}
