package com.interview.notes.code.months.Oct23.test5;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 You have a browser of one tab where you start on the homepage and you can
 visit another url, get back in the history number of steps or move forward in
 the history number of steps .
 Implement the BrowserHistory class:
 •    BrowserHistory(string homepage) Initializes the object with the
 homepage of the browser.
 •    void visit(string url) Visits url from the current page. It clears up
 all the forward history.
 •  string back(int steps) Move steps back in history. If you can only
 return x steps in the history and steps > x , you will return only x
 steps. Return the current url after moving back in history at most
 steps .
 •    string forward(int steps) Move steps forward in history. If you
 can only forward x steps in the history and steps > x , you will forward
 only x steps. Return the current url after forwarding in history at most
 steps .
 Example:


 Example:
 Input:
 ["BrowserHistory","visit,,,"visit",wvisit","back","back","forward
 [["leetcode.com"],["google.com"],["facebook.com"],
 ["youtube.com"], [1],[1],[1],["linkedin.com"],[2],[2],[7]]
 Output:
 [null,null,null,null,"facebook.com","google.com","facebook.com", i
 Explanation:
 BrowserHistory browserHistory = new
 BrowserHistory("leetcode.com");
 browserHistory.visit("google.com");
 "leetcode.com". Visit "google.com"
 browserHistory.visit("facebook.com");
 "google.com". Visit "facebook.com"
 browserHistory.visit("youtube.com");
 "facebook.com". Visit "youtube.com"
 browserHistory.back(l);
 // You are in
 // You are in
 // You are in
 // You are in
 "youtube.com", move back to "facebook.com" return
 "facebook.com"
 browserHistory.back(l);                                          // You are in
 "facebook.com", move back to "google.com" return "google.com"
 browserHistory.forward(l);                                      // You are in

 "facebook.com", move back to "google.com" return "google.com"
 browserHistory.forward(l); //  You are in
 "google.com", move forward to "facebook.com" return
 "facebook.com"
 browserHistory.visit("linkedin.com"); // You are in
 "facebook.com". Visit "linkedin.com"
 browserHistory.forward(2);                                       // You are in
 "linkedin.com", you cannot move forward any steps.
 browserHistory.back(2);                                            // You are in
 "linkedin.com", move back two steps to "facebook.com" then to
 "google.com". return "google.com"
 browserHistory.back(7);                                            // You are in
 "google.com", you can move back only one step to
 "leetcode.com". return "leetcode.com"


 Constraints:
 •   1 <= homepage.length <= 20
 •    1 <= url.length <= 20
 •    1 <= steps <= 100
 •  homepage and url consist of or lower case English letters.
 •  At most 5000 calls will be made to visit, back , and forward .


 class BrowserHistory {
 public BrowserHistory(String homepage) {
 }
 public void visit(String url) {
 }
 public String back(int steps) {
 }
 public String forward(int steps) {
 }
 }
 /»*
 *  Your BrowserHistory object will be instantiated
 and called as such:
 *  BrowserHistory obj = new BrowserHistory(homepage);
 *  obi.visit(url):

 */
class BrowserHistory {
    private LinkedList<String> historyList;
    private ListIterator<String> iter;

    public BrowserHistory(String homepage) {
        historyList = new LinkedList<>();
        historyList.add(homepage);
        iter = historyList.listIterator();
        iter.next();  // Point to the homepage
    }

    public void visit(String url) {
        // Remove all elements after the current one.
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        iter.add(url);
        iter.next();  // Point to the new url
    }

    public String back(int steps) {
        int moves = 0;
        while (iter.hasPrevious() && moves < steps) {
            iter.previous();
            moves++;
        }
        return iter.next();
    }

    public String forward(int steps) {
        int moves = 0;
        while (iter.hasNext() && moves < steps) {
            iter.next();
            moves++;
        }
        return iter.previous();
    }
}
