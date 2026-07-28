package com.interview.notes.code.year.y2026.july.common.test9;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Outcome {
    public static List<List<String>> solve(List<String> animalIDs, List<String> lactDates) {
        Map<String, List<LocalDate>> dates = IntStream.range(0, animalIDs.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        animalIDs::get,
                        Collectors.mapping(
                                i -> LocalDate.parse(lactDates.get(i)),
                                Collectors.toList()
                        )
                ));

        return dates.entrySet().stream()
                .map(entry -> {
                    List<LocalDate> sortedDates = entry.getValue().stream()
                            .sorted()
                            .collect(Collectors.toList());

                    double average = IntStream.range(1, sortedDates.size())
                            .mapToLong(i -> ChronoUnit.DAYS.between(
                                    sortedDates.get(i - 1),
                                    sortedDates.get(i)
                            ))
                            .average()
                            .orElse(0);

                    return Arrays.asList(
                            entry.getKey(),
                            String.format(Locale.US, "%.1f", average)
                    );
                })
                .sorted(Comparator
                        .<List<String>>comparingDouble(
                                row -> Double.parseDouble(row.get(1))
                        )
                        .reversed()
                        .thenComparing(row -> new BigInteger(row.get(0))))
                .collect(Collectors.toList());
    }

    static void test(
            String name,
            List<String> ids,
            List<String> dates,
            List<List<String>> expected
    ) {
        List<List<String>> actual = solve(ids, dates);
        System.out.println(
                name + ": " + (actual.equals(expected) ? "PASS" : "FAIL")
        );
    }

    public static void main(String[] args) {
        test(
                "Example 1",
                Arrays.asList(
                        "2188734000014",
                        "2188734000014",
                        "2188734000016",
                        "2188734000016",
                        "2188734000016"
                ),
                Arrays.asList(
                        "2016-02-08",
                        "2016-06-10",
                        "2016-04-19",
                        "2016-05-12",
                        "2016-06-25"
                ),
                Arrays.asList(
                        Arrays.asList("2188734000014", "123.0"),
                        Arrays.asList("2188734000016", "33.5")
                )
        );

        test(
                "Unsorted dates",
                Arrays.asList("2", "2", "2", "1", "1"),
                Arrays.asList(
                        "2024-01-21",
                        "2024-01-01",
                        "2024-01-11",
                        "2024-02-01",
                        "2024-01-01"
                ),
                Arrays.asList(
                        Arrays.asList("1", "31.0"),
                        Arrays.asList("2", "10.0")
                )
        );

        test(
                "Equal averages",
                Arrays.asList("10", "10", "2", "2"),
                Arrays.asList(
                        "2024-01-01",
                        "2024-01-11",
                        "2024-02-01",
                        "2024-02-11"
                ),
                Arrays.asList(
                        Arrays.asList("2", "10.0"),
                        Arrays.asList("10", "10.0")
                )
        );

        List<String> largeIds = Collections.nCopies(250, "999");

        List<String> largeDates = IntStream.range(0, 250)
                .mapToObj(i -> LocalDate.of(2020, 1, 1)
                        .plusDays(i)
                        .toString())
                .collect(Collectors.toList());

        test(
                "Large input",
                largeIds,
                largeDates,
                Collections.singletonList(
                        Arrays.asList("999", "1.0")
                )
        );
    }
}