package com.interview.notes.code.year.y2026.jan.common.test4;

import java.util.ArrayList;
import java.util.List;

public class ListIterationDemo {

    public static void main(String[] args) {

        // ---------- CASE 1 ----------
        List<String> data1 = new ArrayList<>();
        data1.add("a");
        data1.add("z");
        data1.add("c");
        data1.add("p");

        for (int i = 0; i < data1.size(); i++) {
            if (data1.get(i).equals("c")) {
                data1.remove(i);
            }
        }
        System.out.println("Case 1 size = " + data1.size());
        System.out.println("Case 1 data = " + data1);

        // ---------- CASE 2 ----------
        List<String> data2 = new ArrayList<>();
        data2.add("a");
        data2.add("z");
        data2.add("c");
        data2.add("p");

        try {
            for (String element : data2) {
                if (element.equals("z")) {
                    data2.remove(element);
                }
            }
        } catch (Exception e) {
            System.out.println("Case 2 exception = " + e);
        }
        System.out.println("Case 2 data = " + data2);

        // ---------- CASE 3 ----------
        List<String> data3 = new ArrayList<>();
        data3.add("a");
        data3.add("z");
        data3.add("c");
        data3.add("p");

        try {
            data3.forEach(element -> {
                if (element.equals("a")) {
                    data3.remove(element);
                }
            });
        } catch (Exception e) {
            System.out.println("Case 3 exception = " + e);
        }
        System.out.println("Case 3 data = " + data3);
    }
}
