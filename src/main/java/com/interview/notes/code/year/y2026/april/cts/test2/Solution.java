package com.interview.notes.code.year.y2026.april.cts.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Device {
    private final String name;
    private final int code;

    Device(String name, int code) {
        this.name = name; // assign name
        this.code = code; // assign code
    }

    String getName() { return name; } // getter
    int getCode() { return code; } // getter

    public String toString() {
        return name + "-" + code; // print
    }
}

public class Solution {
    public static void main(String[] args) {

        List<Device> list = new ArrayList<>();

        list.add(new Device("Mobile", 102));
        list.add(new Device("Laptop", 101));
        list.add(new Device("Tablet", 103));
        list.add(new Device("Watch", 100));

        // SORT BY NAME (old way)
        Collections.sort(list, new Comparator<Device>() {
            public int compare(Device a, Device b) {
                return a.getName().compareTo(b.getName()); // compare names
            }
        });

        System.out.println("Sort by Name:");
        for (Device d : list)
            System.out.println(d);

        // SORT BY CODE (old way)
        Collections.sort(list, new Comparator<Device>() {
            public int compare(Device a, Device b) {
                return a.getCode() - b.getCode(); // compare codes
            }
        });

        System.out.println("\nSort by Code:");
        for (Device d : list)
            System.out.println(d);

        // test
        test(list.get(0).getCode() == 100, "Comparator Sort");
    }

    static void test(boolean cond, String name) {
        System.out.println(name + (cond ? " PASS" : " FAIL"));
    }
}