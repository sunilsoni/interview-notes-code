package com.interview.notes.code.months.oct24.tst24;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {
        // 1. Corrected
        List<Integer> list1 = new ArrayList<Integer>();

        // 2. Already correct
        List<Integer> list2 = new ArrayList<Integer>();

        // 3. Corrected
        List<Number> list3 = new ArrayList<Number>();

        // 4. Corrected
        List<Integer> list4 = new ArrayList<Integer>();
    }
}
