package com.interview.notes.code.year.y2025.June.common.test1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class DateGroupingExample {
    public static void main(String[] args) {
        // Create ArrayList with test dates
        ArrayList<String> dateList = new ArrayList<>();
        dateList.add("01-Jan-2025");
        dateList.add("10-Jan-2025");
        dateList.add("03-Feb-2025");
        dateList.add("06-Feb-2025");
        dateList.add("10-Mar-2025");

        // Define date formatters
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM-yyyy");

        // Process dates using Stream API
        Map<String, Long> result = dateList.stream()
                .map(dateStr -> LocalDate.parse(dateStr, inputFormatter))
                .map(date -> date.format(outputFormatter))
                .collect(Collectors.groupingBy(
                        month -> month,
                        Collectors.counting()
                ));

        // Print results
        System.out.println("Original dates:");
        dateList.forEach(System.out::println);
        
        System.out.println("\nGrouped by month with count:");
        result.forEach((month, count) -> 
            System.out.println(month + ", " + count));
    }
}
