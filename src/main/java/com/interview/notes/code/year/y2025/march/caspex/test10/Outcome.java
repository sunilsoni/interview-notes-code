package com.interview.notes.code.year.y2025.march.caspex.test10;

import java.util.*;

public class Outcome {
    public static List<Integer> solve(int capacity, List<String> ar) {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>(capacity, 0.75f, true);
        List<Integer> results = new ArrayList<>();

        for (String operation : ar) {
            String[] parts = operation.split(",");
            if (parts[0].equals("GET")) {
                int key = Integer.parseInt(parts[1]);
                results.add(cache.getOrDefault(key, -1));
            } else if (parts[0].equals("PUT")) {
                int key = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                cache.put(key, value);
                if (cache.size() > capacity) {
                    int oldest = cache.keySet().iterator().next();
                    cache.remove(oldest);
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> test1 = Arrays.asList("GET,2", "PUT,1,100", "PUT,2,125", "PUT,3,150", "GET,1", "GET,3");
        List<Integer> res1 = solve(2, test1);
        System.out.println(res1.equals(Arrays.asList(-1, -1, 150)) ? "pass" : "fail");

        List<String> test2 = Arrays.asList("PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22");
        List<Integer> res2 = solve(3, test2);
        System.out.println(res2.equals(Arrays.asList(75, 50)) ? "pass" : "fail");

        List<String> test3 = Arrays.asList("PUT,1,1", "GET,1", "GET,2");
        List<Integer> res3 = solve(1, test3);
        System.out.println(res3.equals(Arrays.asList(1, -1)) ? "pass" : "fail");
    }
}