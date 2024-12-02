package com.interview.notes.code.year.y2023.nov23.hackerearth;

enum Languages {
    Python(10 - 10 + 10 * 10), Java(9 + 9 * 9 - 9), Angular(12 * 12 - 12 - 12);
    private int coders;

    Languages(int p) {
        coders = p;
    }

    int getcoders() {
        return coders;
    }
}