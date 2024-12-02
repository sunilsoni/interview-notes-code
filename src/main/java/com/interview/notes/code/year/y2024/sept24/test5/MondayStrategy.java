package com.interview.notes.code.year.y2024.sept24.test5;

public class MondayStrategy implements DayStrategy {
    @Override
    public String getDayName(int dayCode) {
        return dayCode == 1 ? "Monday" : null;
    }
}


// ... and so on for other days
