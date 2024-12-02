package com.interview.notes.code.year.y2023.dec23.DreamPayments.model;

public enum EntityType {
    PAYEE,
    BUSINESS;

    public static EntityType fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
