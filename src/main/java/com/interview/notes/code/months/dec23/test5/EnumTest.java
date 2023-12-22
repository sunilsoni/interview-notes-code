package com.interview.notes.code.months.dec23.test5;

public class EnumTest {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Day currentDay = Day.valueOf(args[0].toUpperCase());
                Day nextDay = currentDay.nextDay();
                System.out.println("The next day after " + currentDay + " is " + nextDay + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day. Please enter a valid day of the week (e.g., Sunday, Monday, etc.).");
            }
        } else {
            System.out.println("No day provided. Please enter a day of the week.");
        }
    }
}