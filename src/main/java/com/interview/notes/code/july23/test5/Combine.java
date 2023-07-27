package com.interview.notes.code.july23.test5;
import java.util.*;
import java.util.stream.*;
public class Combine {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("One", "Two", "Three");
        List<Integer> intList = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = foo(strList, intList);

        result.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }


    public static Map<String,Integer> foo(List<String> strList ,List<Integer> intList){
        if (strList.size() != intList.size()) {
            throw new IllegalArgumentException("Both lists should have the same size");
        }

        return IntStream.range(0, strList.size())
                .boxed()
                .collect(Collectors.toMap(strList::get, intList::get));
    }
}
