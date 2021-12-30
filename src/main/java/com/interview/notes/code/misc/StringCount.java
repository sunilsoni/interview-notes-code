package com.interview.notes.code.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCount {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Tomy","Rahul","Boboy","John","Rahul","John");


        Map<String, Long> nameCountMap =  names.stream()
                .collect(Collectors.toMap(Function.identity(), v->1l,Long::sum) )
                .entrySet()
                .stream()
                .filter(v -> v.getValue()>1)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        System.out.println(nameCountMap);

    }


}
