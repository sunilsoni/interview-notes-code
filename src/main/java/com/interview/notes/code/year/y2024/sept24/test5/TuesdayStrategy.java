package com.interview.notes.code.year.y2024.sept24.test5;

public class TuesdayStrategy implements DayStrategy {
    @Override
    public String getDayName(int dayCode) {
        return dayCode == 2 ? "Tuesday" : null;
    }
}