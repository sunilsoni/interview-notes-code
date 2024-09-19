package com.interview.notes.code.months.sept24.wallmart.test3;

import java.util.*;

class BadgeRecord {
    private String employeeName;
    private String action; // "enter" or "exit"

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
