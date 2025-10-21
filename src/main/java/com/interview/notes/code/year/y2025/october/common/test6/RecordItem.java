package com.interview.notes.code.year.y2025.october.common.test6;

public class RecordItem {
    private final String name;
    private final String type;
    private int recordNumber;

    // Constructor, getters, setters
    public RecordItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setRecordNumber(int num) {
        this.recordNumber = num;
    }
}

