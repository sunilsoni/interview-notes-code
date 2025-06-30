package com.interview.notes.code.year.y2025.June.common.test8;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            throw new IOException();
        } catch (RuntimeException | IOException e) { // Line 1
            if (e instanceof IOException) {
                // Line 2 - Compilation error: e is effectively final and can't be reassigned
//                e = new RuntimeException(e);
//                throw e;
            }
        }
    }
}
