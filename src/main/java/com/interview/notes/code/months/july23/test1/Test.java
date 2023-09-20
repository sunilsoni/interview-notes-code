package com.interview.notes.code.months.july23.test1;

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Long a = new Long(100);
        Long b = new Long(100);

        System.out.println(a == b);//false
        System.out.println(a.equals(b));//true

        Long c = Long.valueOf(100);
        Long d = Long.valueOf(100);

        System.out.println(c == d);//true
        System.out.println(c.equals(d));//true

        String input = "An Application Developer";
        Character output = input.chars()
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(output);//a
    }
}
