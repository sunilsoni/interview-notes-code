package com.interview.notes.code.year.y2025.november.Amazon.test4;

// Snake.java
public class Snake extends Jump {
    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) {
            throw new IllegalArgumentException("Snake must go down: start must be > end");
        }
    }

    @Override
    public String getJumpType() {
        return "SNAKE";
    }
}