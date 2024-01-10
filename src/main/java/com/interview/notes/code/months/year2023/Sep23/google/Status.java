package com.interview.notes.code.months.year2023.Sep23.google;

class Status {
    public int timestamp;
    public String message;

    public Status(int timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    @Override
    public String toString() {
        return timestamp + " " + message;
    }
}