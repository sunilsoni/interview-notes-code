package com.interview.notes.code.months.dec23.DreamPayments.model;

public enum EntityStatus {
    VERIFIED,
    NOT_VERIFIED,
    TERMINATED;

    public static EntityStatus fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
