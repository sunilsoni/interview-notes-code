package com.interview.notes.code.months.july24.test17;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> codes = Arrays.asList("1st", "2nd", "3rd", "4th");
        System.out.println(
                codes.stream()
                        .filter(s -> s.endsWith("d"))
                        .reduce((s1, s2) -> s1 + s2)
        );
    }
}
