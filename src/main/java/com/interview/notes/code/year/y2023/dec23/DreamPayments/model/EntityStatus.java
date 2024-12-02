package com.interview.notes.code.year.y2023.dec23.DreamPayments.model;

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
