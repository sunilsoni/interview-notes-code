package com.interview.notes.code.year.y2023.dec23.test5;

public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public Day nextDay() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}