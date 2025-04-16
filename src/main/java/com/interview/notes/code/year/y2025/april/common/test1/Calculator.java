package com.interview.notes.code.year.y2025.april.common.test1;

interface Addition {
    default int calc(int a, int b) {
        return a + b;
    }
}

interface Subtract {
    default int calc(int a, int b) {
        return a - b;
    }
}

class Calculator implements Addition, Subtract {
    @Override
    public int calc(int a, int b) {
        // Explicitly call the desired default method
        return Addition.super.calc(a, b); // Or Subtract.super.calc(a, b)
    }
}
