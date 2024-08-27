package com.interview.notes.code.months.aug24.test28;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PairFinder {
    public static String findPairs(int[] arr) {
        Map<Integer, Long> countMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String pairs = countMap.entrySet().stream()
                .filter(entry -> countMap.containsKey(entry.getValue().intValue()) && entry.getKey() != entry.getValue().intValue())
                .map(entry -> "(" + entry.getKey() + "," + entry.getValue() + ")")
                .sorted()
                .collect(Collectors.joining());

        return "{" + pairs + "}";
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 2, 2, 3};
        System.out.println(findPairs(a));
    }
}
