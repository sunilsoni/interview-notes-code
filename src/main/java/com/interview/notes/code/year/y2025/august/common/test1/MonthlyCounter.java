package com.interview.notes.code.year.y2025.august.common.test1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MonthlyCounter {
    // Formatter for our Month-Year key
    private static final DateTimeFormatter MONTH_YEAR_FMT =
            DateTimeFormatter.ofPattern("MMM-yyyy", Locale.ENGLISH);

    public static Map<String, Integer> countByMonth(List<Date> dates) {
        return dates.stream()
                // 1) convert java.util.Date → java.time.LocalDate
                .map(d -> d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                // 2) format to "MMM-yyyy"
                .map(ld -> ld.format(MONTH_YEAR_FMT))
                // 3) group and count, then convert Long→Integer
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        List<Date> dates = Arrays.asList(
                sdf.parse("01-Jan-2025"),
                sdf.parse("02-Jan-2025"),
                sdf.parse("03-Feb-2025"),
                sdf.parse("04-Feb-2025"),
                sdf.parse("06-Mar-2025")
        );

        Map<String, Integer> result = countByMonth(dates);
        // print it out
        result.forEach((month, count) -> System.out.println(month + " → " + count));
    }
}