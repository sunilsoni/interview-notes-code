package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.ArrayList;
import java.util.Iterator;

public class MyClass {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("One");
        arr.add("Two");
        arr.add("Three");
        arr.add("Four");
        try {
            // Printing the elements

            System.out.println("ArrayList: ");
            Iterator<String> iter = arr.iterator();
            while (iter.hasNext()) {
                System.out.print(iter.next() + ", ");
                arr.add("Five");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}