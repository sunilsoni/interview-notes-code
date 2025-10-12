package com.interview.notes.code.year.y2025.october.assessments.test1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<List<String>> getProductSuggestions(List<String> products, String search) {
        List<String> sorted = products.stream().sorted().collect(Collectors.toList());
        List<List<String>> out = new ArrayList<>();
        for (int i = 1; i <= search.length(); i++) {
            String prefix = search.substring(0, i);
            int idx = lowerBound(sorted, prefix);
            List<String> row = new ArrayList<>(3);
            int picked = 0;
            while (idx < sorted.size() && picked < 3) {
                String s = sorted.get(idx);
                if (!s.startsWith(prefix)) break;
                row.add(s);
                idx++;
                picked++;
            }
            out.add(row);
        }
        return out;
    }

    private static int lowerBound(List<String> a, String key) {
        int lo = 0, hi = a.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a.get(mid).compareTo(key) < 0) lo = mid + 1; else hi = mid;
        }
        return lo;
    }

    private static List<List<String>> getProductSuggestionsNaive(List<String> products, String search) {
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= search.length(); i++) {
            String prefix = search.substring(0, i);
            List<String> row = products.stream()
                    .filter(p -> p.startsWith(prefix))
                    .sorted()
                    .limit(3)
                    .collect(Collectors.toList());
            res.add(row);
        }
        return res;
    }

    private static boolean equalNested(List<List<String>> a, List<List<String>> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) if (!a.get(i).equals(b.get(i))) return false;
        return true;
    }

    private static void printPassFail(boolean ok) {
        System.out.println(ok ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        List<String> p1 = Arrays.asList("carpet", "cart", "car", "camera", "crate");
        String s1 = "camera";
        List<List<String>> e1 = Arrays.asList(
                Arrays.asList("camera", "car", "carpet"),
                Arrays.asList("camera", "car", "carpet"),
                List.of("camera"),
                List.of("camera"),
                List.of("camera"),
                List.of("camera")
        );
        printPassFail(equalNested(getProductSuggestions(p1, s1), e1));

        List<String> p2 = Arrays.asList("abcd", "abdc", "abaa", "acbd");
        String s2 = "abad";
        List<List<String>> e2 = Arrays.asList(
                Arrays.asList("abaa", "abcd", "abdc"),
                Arrays.asList("abaa", "abcd", "abdc"),
                List.of("abaa"),
                List.of("abaa")
        );
        printPassFail(equalNested(getProductSuggestions(p2, s2), e2));

        List<String> p3 = Arrays.asList("x", "y", "z");
        String s3 = "a";
        List<List<String>> e3 = List.of(Collections.emptyList());
        printPassFail(equalNested(getProductSuggestions(p3, s3), e3));

        List<String> many = new ArrayList<>();
        for (int i = 0; i < 200; i++) many.add("a" + i);
        many.addAll(Arrays.asList("aa", "aaa", "aaaa", "aab", "aac", "aad"));
        String s4 = "aaa";
        printPassFail(equalNested(getProductSuggestions(many, s4), getProductSuggestionsNaive(many, s4)));

        int n = 1000;
        Random r = new Random(123456);
        List<String> big = new ArrayList<>(n);
        while (big.size() < n) {
            int len = 1 + r.nextInt(12);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) sb.append((char) ('a' + r.nextInt(26)));
            big.add(sb.toString());
        }
        String bigSearch = "abcdefghij";
        boolean largeOk = equalNested(getProductSuggestions(big, bigSearch), getProductSuggestionsNaive(big, bigSearch));
        System.out.println("Large input test passed: " + largeOk);
    }
}
