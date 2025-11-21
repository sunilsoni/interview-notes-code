package com.interview.notes.code.misc.IKMTest;

import java.util.ArrayList;
import java.util.List;

class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add(0, "3");
        list.add(1, "4");
        list.forEach(System.out::println);
    }
}