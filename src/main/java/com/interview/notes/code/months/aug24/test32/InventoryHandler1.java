package com.interview.notes.code.months.aug24.test32;

import java.util.List;
import java.util.stream.Collectors;


class Car {
    private String name;
    private int year;
    private int miles;

    // Constructor
    public Car(String name, int year, int miles) {
        this.name = name;
        this.year = year;
        this.miles = miles;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getMiles() {
        return miles;
    }
}
