package com.interview.notes.code.months.jan24.test3;

public class DayOfWeekDemo {
    // Enum for days of the week
    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    // Method to get the next day
    public static DayOfWeek getNextDay(DayOfWeek currentDay) {
        // Find the next day by incrementing the ordinal value and using modulo for wrap-around
        return DayOfWeek.values()[(currentDay.ordinal() + 1) % DayOfWeek.values().length];
    }

    // Main method for example execution
    public static void main(String[] args) {
        DayOfWeek today = DayOfWeek.MONDAY;
        DayOfWeek tomorrow = getNextDay(today);
        System.out.println("If today is " + today + ", then tomorrow is " + tomorrow + ".");
    }
}

