package com.interview.notes.code.july23.test12;

import java.util.ArrayList;
import java.util.List;

/**
 * You have a browser of one tab where you start on the homepage and you can visit another url , get back in the history number of
 * steps or move forward in the history number of steps .
 * Implement the BrowserHistory class:
 * •    BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * •    void visit(string url) Visits url from the current page. It clears up all the forward history.
 * •    string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x , you
 * will return only x steps. Return the current url after moving back in history at most steps .
 * •    string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps >
 * x , you will forward only x steps. Return the current url after forwarding in history at most steps .
 * Example:
 * Input:
 * [•’BrowserHistory”,"visit”, "visit","visit","back", "back","forward", "visit”,"forward","back","back"]
 * [["leetcode.com"], ["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],
 * [7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.c
 * <p>
 * <p>
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");                                              //  You   are    in    "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");                                         //   You   are    in    "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");                                            //  You   are    in    "facebook.com". Visit "youtube.com"
 * browserHistory.back(l); // You are in "youtube.com", move back to "facebook.com" return
 * "facebook.com”
 * browserHistory.back(l);                                                               //   You   are    in    "facebook.com", move back to "google.com” return
 * "google.com"
 * browserHistory.forward(l);                                                             //  You   are    in    "google.com", move forward to "facebook.com"
 * return "facebook.com"
 * browserHistory.visit("linkedin.com");                                           //   You   are    in    "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                                                          //   You   are    in    "linkedin.com", you cannot move forward any
 * steps.
 * browserHistory.back(2);                                                                //  You   are    in    "linkedin.com", move back two steps to
 * "facebook.com” then to "google.com”. return "google.com"
 * browserHistory.back(7);                                                              // You are in "google.com", you can move back only one step to
 * "leetcode.com”. return "leetcode.com"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * •     1 <= homepage.length <= 20
 * •     1 <= url.length <= 20
 * •     1 <= steps <= 100
 * •    homepage and url consist of or lower case English letters.
 * •   At most 5000 calls will be made to visit , back , and forward .
 */
public class BrowserHistoryGpt {
    private List<String> history; // To store the history of visited URLs
    private int current; // To store the current position within the history

    // Constructor that initializes the object with the homepage of the browser
    public BrowserHistoryGpt(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        current = 0;
    }

    public static void main(String[] args) {
        BrowserHistoryGpt browserHistory = new BrowserHistoryGpt("leetcode.com");
        System.out.println(browserHistory.back(7)); // You are in "leetcode.com", you can move back only 0 step, so it returns "leetcode.com".
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(1)); // You are in "youtube.com", move back to "facebook.com" return "facebook.com”
        System.out.println(browserHistory.back(1)); // You are in "facebook.com", move back to "google.com" return "google.com"
        System.out.println(browserHistory.forward(1)); // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2)); // You are in "linkedin.com", you cannot move forward any steps, so it returns "linkedin.com".
        System.out.println(browserHistory.back(2)); // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        System.out.println(browserHistory.back(7)); // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }

    // Visits a URL from the current page and clears up all the forward history
    public void visit(String url) {
        // Truncate the history after the current page
        history = history.subList(0, current + 1);
        // Add the new URL to the history and update the current position
        history.add(url);
        current++;
    }

    // Move steps back in history, returning the current URL
    public String back(int steps) {
        // Compute how many steps we can actually move back
        int actualSteps = Math.min(steps, current);
        // Update the current position
        current -= actualSteps;
        // Return the current URL
        return history.get(current);
    }

    // Move steps forward in history, returning the current URL
    public String forward(int steps) {
        // Compute how many steps we can actually move forward
        int actualSteps = Math.min(steps, history.size() - current - 1);
        // Update the current position
        current += actualSteps;
        // Return the current URL
        return history.get(current);
    }
}
