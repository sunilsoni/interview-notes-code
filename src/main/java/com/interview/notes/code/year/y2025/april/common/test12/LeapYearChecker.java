package com.interview.notes.code.year.y2025.april.common.test12;

public class LeapYearChecker {
    public static boolean isLeapYear(int year) {
        // If year is divisible by 400, it's a leap year
        if (year % 400 == 0)
            return true;
        
        // If year is divisible by 100, it's not a leap year
        if (year % 100 == 0)
            return false;
        
        // If year is divisible by 4, it's a leap year
        if (year % 4 == 0)
            return true;
        
        // If none of the above conditions are met, it's not a leap year
        return false;
    }

    public static void main(String[] args) {
        // Test cases
        int[] years = {2000, 2004, 2100, 2400, 2023};
        
        for (int year : years) {
            if (isLeapYear(year)) {
                System.out.println(year + " is a leap year");
            } else {
                System.out.println(year + " is not a leap year");
            }
        }
    }
}
