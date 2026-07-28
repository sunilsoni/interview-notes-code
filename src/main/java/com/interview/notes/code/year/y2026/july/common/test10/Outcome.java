package com.interview.notes.code.year.y2026.july.common.test10;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Outcome {

    public static List<List<String>> solve(List<String> animalIDs, List<String> lact_Dates) {
        return IntStream.range(0, animalIDs.size())
            .boxed()
            .collect(Collectors.groupingBy(animalIDs::get,
                Collectors.summarizingLong(i -> LocalDate.parse(lact_Dates.get(i)).toEpochDay())))
            .entrySet().stream()
            .map(e -> Map.entry(e.getKey(), (e.getValue().getMax() - e.getValue().getMin()) / (double) (e.getValue().getCount() - 1)))
            .sorted((a, b) -> {
                int cmp = Double.compare(b.getValue(), a.getValue());
                return cmp != 0 ? cmp : a.getKey().compareTo(b.getKey());
            })
            .map(e -> List.of(e.getKey(), String.format(Locale.US, "%.1f", e.getValue())))
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        test(
            List.of("2188734000014", "2188734000014", "2188734000016", "2188734000016", "2188734000016"),
            List.of("2016-02-08", "2016-06-10", "2016-04-19", "2016-05-12", "2016-06-25"),
            List.of(List.of("2188734000014", "123.0"), List.of("2188734000016", "33.5"))
        );

        test(
            List.of("218873439", "218873439", "218873439", "218873439", "218783439", "218783439", "218783439"),
            List.of("2013-10-25", "2013-11-17", "2013-12-27", "2014-01-20", "2014-01-19", "2014-02-28", "2014-04-10"),
            List.of(List.of("218783439", "40.5"), List.of("218873439", "29.0"))
        );

        String[] largeIds = new String[200000];
        String[] largeDates = new String[200000];
        for (int i = 0; i < 200000; i++) {
            largeIds[i] = i < 100000 ? "1" : "2";
            largeDates[i] = "2020-01-01";
        }
        largeDates[99999] = "2020-01-02";
        largeDates[199999] = "2020-01-03";
        
        test(Arrays.asList(largeIds), Arrays.asList(largeDates),
            List.of(List.of("1", "0.0"), List.of("2", "0.0")));
    }

    private static void test(List<String> ids, List<String> dates, List<List<String>> expected) {
        System.out.println(solve(ids, dates).equals(expected) ? "PASS" : "FAIL");
    }
}