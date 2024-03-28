package com.interview.notes.code.months.march24.test21;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;
import java.util.List;

public class Tweet {
    private long id;
    private List<Media> mediaList; // This replaces the single 'content' field
    private User author;
    private LocalDateTime timestamp;

    // Constructor that initializes mediaList and other properties
    // Methods to add media to the tweet, etc.

    public void displayTweet(MediaRenderer renderer) {
        for (Media media : mediaList) {
            // media.render(renderer);
        }
    }

    public void addMedia(Text text) {
    }
}