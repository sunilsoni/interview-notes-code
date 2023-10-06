package com.interview.notes.code.months.Sep23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharMap {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Peter");
        names.add("Paul");
        names.add("Mary");
        int index = 0;
        for (String name : names) {
            if (name == "Mary") {
                names.remove(index);
            }
            index++;
        }


        StringBuilder sb = new StringBuilder();
        sb.append("aaa").insert(1, "bb").insert(4, "ccc");
        System.out.println(sb);

        String str = "ilovejavatechnology";

        Map<Character, Long> charCounts = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(charCounts);
    }
}
