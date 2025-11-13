package com.interview.notes.code.year.y2025.november.Amazon.test4;

// Ladder.java
public class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException("Ladder must go up: start must be < end");
        }
    }
    
    @Override
    public String getJumpType() {
        return "LADDER";
    }
}
