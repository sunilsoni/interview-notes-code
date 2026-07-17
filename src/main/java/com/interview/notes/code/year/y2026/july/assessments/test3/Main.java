package com.interview.notes.code.year.y2026.july.assessments.test3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> l1 = new ArrayList<>();
        l1.add("arg0");

        ArrayList<String> l2 = new ArrayList<>(l1);

        l2.add("Hi");

        System.out.println("l1 = " + l1);
        System.out.println("l2 = " + l2);
        System.out.println(l1.size() + "," + l2.size());
    }
}