package com.interview.notes.code.year.y2025.july.oracle.test5;// Java Version: Java 8

import java.util.*;
import java.util.stream.Collectors;

/**
 * Defines basic browser navigation operations.
 */
interface NavigationService {
    void visit(String url);

    String back();

    String forward();

    String getCurrentPage();
}

/**
 * Defines bookmark operations.
 */
interface BookmarkService {
    void addBookmark(String url);

    void removeBookmark(String url);

    List<String> listBookmarks();
}

/**
 * Implements NavigationService using two stacks and a currentPage pointer.
 */
class BrowserNavigationService implements NavigationService {
    private Deque<String> backStack = new ArrayDeque<>();    // pages you can go back to
    private Deque<String> forwardStack = new ArrayDeque<>(); // pages you can go forward to
    private String currentPage = null;                       // the page you're on now

    @Override
    public void visit(String url) {
        if (currentPage != null) {
            backStack.push(currentPage);    // save current before navigating
            forwardStack.clear();           // clear forward history
        }
        currentPage = url;                  // set the new current page
    }

    @Override
    public String back() {
        if (backStack.isEmpty()) {
            return currentPage;             // nothing to go back to
        }
        forwardStack.push(currentPage);     // current becomes a forward entry
        currentPage = backStack.pop();      // pop last page
        return currentPage;
    }

    @Override
    public String forward() {
        if (forwardStack.isEmpty()) {
            return currentPage;             // nothing to go forward to
        }
        backStack.push(currentPage);        // current becomes a back entry
        currentPage = forwardStack.pop();   // pop next page
        return currentPage;
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }
}

/**
 * Implements BookmarkService using a LinkedHashSet to preserve insertion order.
 */
class BrowserBookmarkService implements BookmarkService {
    private Set<String> bookmarks = new LinkedHashSet<>();

    @Override
    public void addBookmark(String url) {
        bookmarks.add(url);                 // duplicates automatically ignored
    }

    @Override
    public void removeBookmark(String url) {
        bookmarks.remove(url);              // remove if exists
    }

    @Override
    public List<String> listBookmarks() {
        // return a new list; use streams if we wanted filtering/sorting
        return bookmarks.stream()
                .collect(Collectors.toList());
    }
}

/**
 * Simple test harness in main method.
 * Runs several small and large test cases and prints PASS/FAIL.
 */
public class BrowserBackendSDK {
    public static void main(String[] args) {
        // -- Test 1: Simple navigation --
        BrowserNavigationService nav = new BrowserNavigationService();
        nav.visit("A");
        nav.visit("B");
        nav.visit("C");
        boolean simpleNav = "B".equals(nav.back())       // C -> B
                && "A".equals(nav.back())       // B -> A
                && "B".equals(nav.forward())    // A -> B
                && "C".equals(nav.forward());   // B -> C
        System.out.println("Test Simple Navigation: " + (simpleNav ? "PASS" : "FAIL"));

        // -- Test 2: Large navigation stress test --
        BrowserNavigationService navLarge = new BrowserNavigationService();
        int N = 100_000;
        for (int i = 1; i <= N; i++) {
            navLarge.visit("url" + i);
        }
        // go all the way back
        for (int i = 1; i < N; i++) {
            navLarge.back();
        }
        boolean largeBack = "url1".equals(navLarge.getCurrentPage());
        // then forward to the end
        for (int i = 1; i < N; i++) {
            navLarge.forward();
        }
        boolean largeForward = ("url" + N).equals(navLarge.getCurrentPage());
        System.out.println("Test Large Navigation: " + ((largeBack && largeForward) ? "PASS" : "FAIL"));

        // -- Test 3: Bookmark operations --
        BrowserBookmarkService bm = new BrowserBookmarkService();
        bm.addBookmark("A");
        bm.addBookmark("B");
        bm.addBookmark("A");            // duplicate, should be ignored
        bm.removeBookmark("C");         // non-existent, no effect
        List<String> list = bm.listBookmarks();
        boolean bmCorrect = list.size() == 2
                && list.get(0).equals("A")
                && list.get(1).equals("B");
        System.out.println("Test Bookmark Service: " + (bmCorrect ? "PASS" : "FAIL"));
    }
}