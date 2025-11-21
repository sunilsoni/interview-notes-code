package com.interview.notes.code.year.y2024.march24.test21;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final List<Tweet> tweets;

    public User(String username) {
        this.username = username;
        this.tweets = new ArrayList<>();
    }

    public void postTweet(String content) {
//        Tweet tweet = new Tweet(content, this);
//        tweets.add(tweet);
        // Possibly notify followers about the new tweet here
    }

    // Methods to reply to tweets, get user-specific tweets etc.
}
