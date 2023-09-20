package com.interview.notes.code.months.june23.test11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuplicateString {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("One");
        list.add("Two");
        list.add("Four");

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String str : list) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        // Print all duplicate elements
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Duplicate element: " + entry.getKey());
            }
        }
    }
}
