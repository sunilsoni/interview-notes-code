package com.interview.notes.code.months.dec24.Amazon.test8;

import java.util.ArrayList;
import java.util.List;

public class ListToStringExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("is");
        list.add("Java");

        // Concatenate all elements in the list with a space as delimiter
        String result = String.join(" ", list);

        System.out.println(result);
    }
}
