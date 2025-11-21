package com.interview.notes.code.year.y2025.november.Amazon.test4;

// Jump.java (Abstract class for Snake and Ladder)
public abstract class Jump {
    protected int start;
    protected int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public abstract String getJumpType();
}
