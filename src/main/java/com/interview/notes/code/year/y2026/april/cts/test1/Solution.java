package com.interview.notes.code.year.y2026.april.cts.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

// Device class to hold name and code
class Device {
    private final String name; // store device name
    private final int code;    // store device code

    // constructor to initialize values
    Device(String name, int code) {
        this.name = name; // assign name
        this.code = code; // assign code
    }

    // getter for name
    String getName() {
        return name; // return name
    }

    // getter for code
    int getCode() {
        return code; // return code
    }

    // convert object to readable string
    public String toString() {
        return name + "-" + code; // format output
    }
}

public class Solution {

    public static void main(String[] args) {

        // create list to store devices
        List<Device> devices = new ArrayList<>();

        // add sample data
        devices.add(new Device("Mobile", 102));  // add mobile
        devices.add(new Device("Laptop", 101));  // add laptop
        devices.add(new Device("Tablet", 103));  // add tablet
        devices.add(new Device("Watch", 100));   // add watch

        // ---------- SORT BY NAME ----------
        List<Device> byName =
            devices.stream() // convert list to stream
                   .sorted(Comparator.comparing(Device::getName)) // sort by name
                   .toList(); // collect result

        System.out.println("Sorted by Name:");
        byName.forEach(System.out::println); // print each element

        // ---------- SORT BY CODE ----------
        List<Device> byCode =
            devices.stream() // convert list to stream
                   .sorted(Comparator.comparingInt(Device::getCode)) // sort by code
                   .toList(); // collect result

        System.out.println("\nSorted by Code:");
        byCode.forEach(System.out::println); // print each element

        // ---------- TEST CASES ----------
        test(byName.get(0).getName().equals("Laptop"), "Sort by Name"); // check first element
        test(byCode.get(0).getCode() == 100, "Sort by Code"); // check smallest code

        // ---------- LARGE DATA TEST ----------
        List<Device> big = IntStream.range(0, 100000) // generate 1 lakh data
                .mapToObj(i -> new Device("D" + i, i)) // create device
                .toList();

        long start = System.currentTimeMillis(); // start time

        big.stream()
           .sorted(Comparator.comparingInt(Device::getCode)) // sort large data
           .toList();

        long end = System.currentTimeMillis(); // end time

        System.out.println("Large Data Test PASS in ms: " + (end - start)); // performance check
    }

    // simple test method
    static void test(boolean condition, String name) {
        if (condition)
            System.out.println(name + " -> PASS"); // test passed
        else
            System.out.println(name + " -> FAIL"); // test failed
    }
}