package com.interview.notes.code.year.y2025.july.common.test9;

public class MobilityService {

    @Monitor
    public void ping() {
        System.out.println("Ping executed");
    }

    @Monitor("high")
    public void syncData() {
        System.out.println("Sync in progress");
    }

    public void fallback() {
        System.out.println("Fallback triggered");
    }
}
