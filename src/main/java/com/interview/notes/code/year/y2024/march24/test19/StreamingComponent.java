package com.interview.notes.code.year.y2024.march24.test19;

interface StreamingComponent {
    void load(long timestamp, double number);

    double getAverage();

    double getHigher();
}
