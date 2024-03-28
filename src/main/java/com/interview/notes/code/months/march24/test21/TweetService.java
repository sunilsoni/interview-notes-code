package com.interview.notes.code.months.march24.test21;


import java.util.List;

/**
 * System desing: Design a low-level architecture for a Twitter-like system with functionalities including posting a tweet,
 * replying to tweets, and retrieving all tweets. Utilize Object-Oriented Principles and SOLID principles to create classes, interfaces, and demonstrate these concepts.
 * Design an architecture that is modular, maintainable, and extensible.
 */

public interface TweetService {
    void postTweet(User user, String content);

    void replyToTweet(long tweetId, String content, User replier);

    List<Tweet> getAllTweets();
    // Additional methods as needed
}
