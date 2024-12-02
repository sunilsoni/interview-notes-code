package com.interview.notes.code.year.y2023.Aug23.test1;

public class DayOfWeekExample {

    // Method to get the next day
    public static DayOfWeek getNextDay(DayOfWeek currentDay) {
        DayOfWeek[] days = DayOfWeek.values();
        int currentIndex = currentDay.ordinal();
        int nextIndex = (currentIndex + 1) % days.length;
        return days[nextIndex];
    }

    public static void main(String[] args) {
        DayOfWeek today = DayOfWeek.TUESDAY;
        DayOfWeek nextDay = getNextDay(today);
        System.out.println("Today is " + today);
        System.out.println("Next day is " + nextDay);
    }

    // Define the enumeration for days of the week
    enum DayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }
}
