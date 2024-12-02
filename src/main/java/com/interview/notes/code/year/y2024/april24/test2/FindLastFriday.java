package com.interview.notes.code.year.y2024.april24.test2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FindLastFriday {

    public static void main(String[] args) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Find the last day of the previous month
        LocalDate lastDayOfPreviousMonth = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        // Find the last Friday of the previous month
        LocalDate lastFriday = lastDayOfPreviousMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));

        System.out.println("Last Friday of the previous month: " + lastFriday);
    }
}

