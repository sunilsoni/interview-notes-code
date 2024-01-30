package com.interview.notes.code.months.jan24.test3;

public class WeekDays {
    // Method to get the next day of the week
    public static Day getNextDay(Day currentDay) {
        switch (currentDay) {
            case MONDAY:
                return Day.TUESDAY;
            case TUESDAY:
                return Day.WEDNESDAY;
            case WEDNESDAY:
                return Day.THURSDAY;
            case THURSDAY:
                return Day.FRIDAY;
            case FRIDAY:
                return Day.SATURDAY;
            case SATURDAY:
                return Day.SUNDAY;
            case SUNDAY:
                return Day.MONDAY;
            default:
                throw new IllegalArgumentException("Invalid day");
        }
    }

    // Main method for example execution
    public static void main(String[] args) {
        Day today = Day.MONDAY; // Example: today is Monday
        Day nextDay = getNextDay(today); // Get the next day
        System.out.println("Today is " + today + ", next day is " + nextDay);
    }

    // Enum representing the days of the week
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
