package com.interview.notes.code.months.april24.test14;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SecondFridayOfNextMonth {

    public static void main(String[] args) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the first day of the next month
        LocalDate firstDayOfNextMonth = currentDate.plusMonths(1).withDayOfMonth(1);

        // Calculate the second Friday of the next month
        LocalDate secondFridayOfNextMonth = firstDayOfNextMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).plusWeeks(1);

        // Print the second Friday of the next month
        System.out.println("Second Friday of next month: " + secondFridayOfNextMonth);
    }
}
