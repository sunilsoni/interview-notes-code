package com.interview.notes.code.year.y2024.jan24.test1;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static void main(String[] args) {
        // Example usage:
        DayOfWeek today = DayOfWeek.MONDAY;
        DayOfWeek nextDay = today.getNextDay();
        System.out.println("Today is " + today);
        System.out.println("Next day is " + nextDay);
    }

    // Define a method to get the next day in the sequence
    public DayOfWeek getNextDay() {
        // Get the values of the enum as an array
        DayOfWeek[] days = DayOfWeek.values();

        // Find the index of the current day in the array
        int currentDayIndex = Arrays.asList(days).indexOf(this);

        // Calculate the index of the next day, considering wrapping around to Monday
        int nextDayIndex = (currentDayIndex + 1) % days.length;

        // Return the next day in the sequence
        return days[nextDayIndex];
    }
}
