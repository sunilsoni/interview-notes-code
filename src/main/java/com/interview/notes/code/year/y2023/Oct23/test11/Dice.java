package com.interview.notes.code.year.y2023.Oct23.test11;

import java.util.Random;

class Dice {
    private final Random random = new Random();

    public int roll() {
        return 1 + random.nextInt(6); // [1,6]
    }
}