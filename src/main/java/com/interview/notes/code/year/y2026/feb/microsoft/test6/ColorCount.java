package com.interview.notes.code.year.y2026.feb.microsoft.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColorCount {

    // method to count colors
    static Map<String, Long> countColors(List<String> list) {

        // group by color and count occurrences
        return list.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static void main(String[] args) {

        // test case 1: small list
        List<String> list = Arrays.asList("red","blue","red","yellow");

        Map<String, Long> result = countColors(list);

        // expected result
        Map<String, Long> expected = Map.of("red",2L,"blue",1L,"yellow",1L);

        // check pass/fail
        System.out.println(result.equals(expected) ? "PASS" : "FAIL");

        // test case 2: empty list
        List<String> empty = List.of();

        System.out.println(countColors(empty).isEmpty() ? "PASS" : "FAIL");

        // test case 3: large data
        List<String> big = Stream.generate(() -> "red").limit(1_000_000).toList();

        Map<String, Long> bigResult = countColors(big);

        System.out.println(bigResult.get("red") == 1_000_000 ? "PASS" : "FAIL");
    }
}
