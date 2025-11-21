package com.interview.notes.code.year.y2024.sept24.wallmart.test3;

class BadgeRecord {
    private final String employeeName;
    private final String action; // "enter" or "exit"

    public BadgeRecord(String employeeName, String action) {
        this.employeeName = employeeName;
        this.action = action;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getAction() {
        return action;
    }
}
