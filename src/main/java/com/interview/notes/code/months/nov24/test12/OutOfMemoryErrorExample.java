package com.interview.notes.code.months.nov24.test12;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorExample {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        try {
            while (true) {
                // Allocate a large array and add it to the list
                list.add(new int[1000_000]);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError caught: " + e.getMessage());
        }
    }
}
