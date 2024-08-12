package com.interview.notes.code.months.aug24.test15;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        Function<char[], String> obj = String::new; // Line 5
        String ss = obj.apply(new char[] {'j', 'a', 'v', 'a'}); // Line 6
        System.out.println(ss);


        List<String> codes = Arrays.asList("1st", "2nd", "3rd", "4th");
        System.out.println(codes.stream()
                .filter(s -> s.endsWith("d"))
                .reduce((s1, s2) -> s1 + s2));

    }
}
