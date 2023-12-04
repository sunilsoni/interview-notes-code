package com.interview.notes.code.months.dec23.DreamPayments.model;

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
