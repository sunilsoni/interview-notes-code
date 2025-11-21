package com.interview.notes.code.year.y2023.Sep23.google;

import java.util.HashMap;
import java.util.Map;

public class DuplicateMessageFilter {

    private static final long WINDOW_SIZE_MILLIS = 10000; // 10 seconds

    private final Map<String, Long> messageSeenTimes = new HashMap<>();

    public static void main(String[] args) {
        DuplicateMessageFilter filter = new DuplicateMessageFilter();

        Status status1 = new Status(10, "solar panel activated");
        Status status2 = new Status(11, "low battery warning");
        Status status3 = new Status(12, "tire one: low air pressure");
        Status status4 = new Status(13, "solar panel activated");
        Status status5 = new Status(14, "low battery warning");
        Status status6 = new Status(21, "solar panel activated");
        Status status7 = new Status(35, "solar panel activated");

        if (filter.shouldDisplayMessage(status1)) {
            System.out.println(status1.message);
        }

        if (filter.shouldDisplayMessage(status2)) {
            System.out.println(status2.message);
        }

        if (filter.shouldDisplayMessage(status3)) {
            System.out.println(status3.message);
        }

        if (filter.shouldDisplayMessage(status4)) {
            System.out.println(status4.message);
        }

        if (filter.shouldDisplayMessage(status5)) {
            System.out.println(status5.message);
        }

        if (filter.shouldDisplayMessage(status6)) {
            System.out.println(status6.message);
        }

        if (filter.shouldDisplayMessage(status7)) {
            System.out.println(status7.message);
        }
    }

    public boolean shouldDisplayMessage(Status status) {
        long now = System.currentTimeMillis();
        Long lastSeenTime = messageSeenTimes.get(status.message);
        if (lastSeenTime == null || now - lastSeenTime > WINDOW_SIZE_MILLIS) {
            messageSeenTimes.put(status.message, now);
            return true;
        } else {
            return false;
        }
    }
}
