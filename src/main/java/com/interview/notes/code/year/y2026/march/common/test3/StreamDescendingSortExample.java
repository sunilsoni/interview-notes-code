package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDescendingSortExample {

    public static void main(String[] args) {

        // create a list to store company names
        List<String> companies = new ArrayList<>();

        // add company names into list
        companies.add("Meta");
        companies.add("Apple");
        companies.add("Amazon");
        companies.add("Netflix");

        // expected result after descending sort
        List<String> expected = Arrays.asList("Netflix","Meta","Apple","Amazon");

        // use Stream API to sort list in descending order
        List<String> result =
                companies.stream() // convert list into stream so we can process elements
                        .sorted(Comparator.reverseOrder()) // sort elements in descending order
                        .collect(Collectors.toList()); // collect sorted elements back into list

        // check result equals expected list
        boolean pass = result.equals(expected);

        // print PASS or FAIL
        System.out.println("Sorted Result : " + result);
        System.out.println(pass ? "PASS" : "FAIL");

        // additional large data test
        largeDataTest();
    }

    static void largeDataTest() {

        // create large list of random strings
        List<String> bigList =
                IntStream.range(0,100000) // generate numbers 0 to 100000
                        .mapToObj(i -> "Company" + i) // convert each number into string
                        .collect(Collectors.toList()); // collect into list

        // sort large dataset using stream descending order
        List<String> sorted =
                bigList.stream() // convert list to stream
                        .sorted(Comparator.reverseOrder()) // descending order sort
                        .collect(Collectors.toList()); // collect result into list

        // verify sorting worked by checking first element
        boolean pass = sorted.get(0).compareTo(sorted.get(sorted.size()-1)) > 0;

        // print result of large dataset test
        System.out.println("Large Data Test : " + (pass ? "PASS" : "FAIL"));
    }
}