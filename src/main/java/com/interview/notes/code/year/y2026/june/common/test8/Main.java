package com.interview.notes.code.year.y2026.june.common.test8;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    static Map<Character, Long> countLetters(String name) { // method to count letters
        return name.toLowerCase() // convert all letters to lowercase
                .chars() // convert string to int stream
                .filter(Character::isLetter) // ignore spaces and special chars
                .mapToObj(c -> (char) c) // convert int to character
                .collect(Collectors.groupingBy(c -> c, TreeMap::new, Collectors.counting())); // group and count
    }

    static void test(String input, Map<Character, Long> expected) { // test method
        var actual = countLetters(input); // get actual output
        System.out.println(actual.equals(expected) ? "PASS" : "FAIL"); // print result
        System.out.println("Input    : " + input); // print input
        System.out.println("Expected : " + expected); // print expected
        System.out.println("Actual   : " + actual); // print actual
        System.out.println(); // empty line
    }

    public static void main(String[] args) { // main method
        test("Abhiram Majidi", Map.of( // test given name
                'a', 3L, 'b', 1L, 'd', 1L, 'h', 1L,
                'i', 3L, 'j', 1L, 'm', 2L, 'r', 1L
        ));

        test("", Map.of()); // empty input test

        test("A A a", Map.of('a', 3L)); // uppercase lowercase test

        test("Java 21!!", Map.of('a', 2L, 'j', 1L, 'v', 1L)); // special char test

        var big = "AbhiramMajidi".repeat(100000); // large data input
        System.out.println(countLetters(big).get('a') == 300000 ? "PASS Large Data" : "FAIL Large Data"); // large test
    }
}