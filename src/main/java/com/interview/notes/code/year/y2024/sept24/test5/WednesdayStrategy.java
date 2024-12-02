package com.interview.notes.code.year.y2024.sept24.test5;

public class WednesdayStrategy implements DayStrategy {
    @Override
    public String getDayName(int dayCode) {
        return dayCode == 3 ? "Wednesday" : null;
    }
}