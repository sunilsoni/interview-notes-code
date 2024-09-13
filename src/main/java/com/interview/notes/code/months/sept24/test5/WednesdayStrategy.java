package com.interview.notes.code.months.sept24.test5;

public class WednesdayStrategy implements DayStrategy {
    @Override
    public String getDayName(int dayCode) {
        return dayCode == 3 ? "Wednesday" : null;
    }
}