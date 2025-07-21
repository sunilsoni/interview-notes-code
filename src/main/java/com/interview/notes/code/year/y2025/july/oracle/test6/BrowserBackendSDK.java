package com.interview.notes.code.year.y2025.july.oracle.test6;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Navigation API for browser history.
 */
interface NavigationService {
    void visit(BrowserPage page);

    BrowserPage back();

    BrowserPage forward();

    BrowserPage getCurrentPage();
}

/**
 * Bookmark API for BrowserPage.
 */
interface BookmarkService {
    void addBookmark(BrowserPage page);

    void removeBookmark(BrowserPage page);

    List<BrowserPage> listBookmarks();
}

/**
 * Represents a visited browser page with URL, title, and timestamp.
 */
class BrowserPage {
    private String url;
    private String title;
    private long timestamp;

    public BrowserPage(String url, String title) {
        this.url = url;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Page[url=" + url + ", title=" + title + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrowserPage)) return false;
        BrowserPage that = (BrowserPage) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title);
    }
}

/**
 * Keeps track of back/forward stacks of BrowserPage.
 */
class BrowserHistory implements NavigationService {
    private Deque<BrowserPage> backStack = new ArrayDeque<>();
    private Deque<BrowserPage> forwardStack = new ArrayDeque<>();
    private BrowserPage currentPage = null;

    @Override
    public void visit(BrowserPage page) {
        if (currentPage != null) {
            backStack.push(currentPage);
            forwardStack.clear();
        }
        currentPage = page;
    }

    @Override
    public BrowserPage back() {
        if (backStack.isEmpty()) return currentPage;
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        return currentPage;
    }

    @Override
    public BrowserPage forward() {
        if (forwardStack.isEmpty()) return currentPage;
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        return currentPage;
    }

    @Override
    public BrowserPage getCurrentPage() {
        return currentPage;
    }
}

/**
 * Stores bookmarks in insertion order, ignores duplicates.
 */
class BrowserBookmarkService implements BookmarkService {
    private Set<BrowserPage> bookmarks = new LinkedHashSet<>();

    @Override
    public void addBookmark(BrowserPage page) {
        bookmarks.add(page);
    }

    @Override
    public void removeBookmark(BrowserPage page) {
        bookmarks.remove(page);
    }

    @Override
    public List<BrowserPage> listBookmarks() {
        return bookmarks.stream().collect(Collectors.toList());
    }
}

/**
 * Test harness with main() printing PASS/FAIL, including large-data stress tests.
 */
public class BrowserBackendSDK {
    public static void main(String[] args) {
        // --- Test 1: Simple navigation with BrowserPage ---
        BrowserHistory history = new BrowserHistory();
        BrowserPage pA = new BrowserPage("http://a.com", "A");
        BrowserPage pB = new BrowserPage("http://b.com", "B");
        BrowserPage pC = new BrowserPage("http://c.com", "C");

        history.visit(pA);
        history.visit(pB);
        history.visit(pC);

        boolean nav1 = pB.equals(history.back())    // from C to B
                && pA.equals(history.back())    // from B to A
                && pB.equals(history.forward()) // from A to B
                && pC.equals(history.forward()); // from B to C

        System.out.println("Test Simple Navigation: " + (nav1 ? "PASS" : "FAIL"));

        // --- Test 2: Large navigation stress test ---
        BrowserHistory largeHist = new BrowserHistory();
        int N = 100_000;
        for (int i = 1; i <= N; i++) {
            largeHist.visit(new BrowserPage("url" + i, "Page" + i));
        }
        for (int i = 1; i < N; i++) {
            largeHist.back();
        }
        boolean backOK = "url1".equals(largeHist.getCurrentPage().getUrl());

        for (int i = 1; i < N; i++) {
            largeHist.forward();
        }
        boolean forwardOK = ("url" + N).equals(largeHist.getCurrentPage().getUrl());

        System.out.println("Test Large Navigation: " + ((backOK && forwardOK) ? "PASS" : "FAIL"));

        // --- Test 3: Bookmark service with BrowserPage ---
        BrowserBookmarkService bm = new BrowserBookmarkService();
        bm.addBookmark(pA);
        bm.addBookmark(pB);
        bm.addBookmark(pA); // duplicate
        bm.removeBookmark(new BrowserPage("http://x.com", "X")); // no-op

        List<BrowserPage> list = bm.listBookmarks();
        boolean bmOK = list.size() == 2
                && list.get(0).equals(pA)
                && list.get(1).equals(pB);

        System.out.println("Test Bookmark Service: " + (bmOK ? "PASS" : "FAIL"));
    }
}