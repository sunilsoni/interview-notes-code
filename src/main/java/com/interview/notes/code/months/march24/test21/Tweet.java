package com.interview.notes.code.months.march24.test21;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private long id;
    private String content;
    private User author;
    private LocalDateTime timestamp;
    private List<Tweet> replies;

    public Tweet(String content, User author) {
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.replies = new ArrayList<>();
    }

    // Methods to get, add replies etc.
}
