package com.interview.notes.code.year.y2025.may.common.test15;

enum Bus {
    DIESEL, ELECTRIC, HYBRID;

    int mileage = 10; // This causes a compile-time error
}

public class EnumTest2 {
    public static void main(String[] args) {
        Bus bus = Bus.ELECTRIC;
        System.out.println(bus + ", " + bus.mileage);
    }
}
