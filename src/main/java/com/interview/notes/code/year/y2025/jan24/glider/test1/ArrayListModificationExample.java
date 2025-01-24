package com.interview.notes.code.year.y2025.jan24.glider.test1;

import java.util.ArrayList;
import java.util.List;

public class ArrayListModificationExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        for (String item : list) {
            if (item.equals("two")) {
                list.remove(item); // This causes ConcurrentModificationException
            }
        }

        System.out.println(list);
    }
}
