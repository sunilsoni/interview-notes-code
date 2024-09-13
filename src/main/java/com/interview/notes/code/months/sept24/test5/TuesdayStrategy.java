package com.interview.notes.code.months.sept24.test5;

public class TuesdayStrategy implements DayStrategy {
    @Override
    public String getDayName(int dayCode) {
        return dayCode == 2 ? "Tuesday" : null;
    }
}