package com.interview.notes.code.months.march24.test16;

import java.util.Arrays;
import java.util.List;

public class PrefixHierarchy {

    public static int[] findCompletePrefixes(List<String> names, List<String> queries) {
        return queries.stream()
                .mapToInt(query -> (int) names.stream().filter(name -> name.startsWith(query)).count())
                .toArray();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("steve", "stevens", "danny", "steves", "dan", "john", "johnny", "joe", "alex", "alexander");
        List<String> queries = Arrays.asList("steve", "alex", "joe", "john", "dan");

        int[] result = findCompletePrefixes(names, queries);
        for (int count : result) {
            System.out.println(count);
        }
    }
}
