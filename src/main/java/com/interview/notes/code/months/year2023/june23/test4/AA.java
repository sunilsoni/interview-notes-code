package com.interview.notes.code.months.year2023.june23.test4;

class AA {

    public int calculate() {

        int value = 200;
        try {

//business logic here
            value = computeFInalValue();
        } finally {
            return value;
        }

    }

    private int computeFInalValue() {
        return 100;
    }
}
