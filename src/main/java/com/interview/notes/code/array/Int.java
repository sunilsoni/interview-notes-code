package com.interview.notes.code.array;

import java.util.Arrays;
import java.util.OptionalInt;

public class Int {

    public static void main(String[] args) {
        int[] numbers = {11, 13, 21, 31, 41};

        OptionalInt result = Arrays.stream(numbers)
                .filter(n -> n / 10 == 1)
                .findFirst();

        if (result.isPresent()) {
            System.out.println("The first integer starting with 1 is: " + result.getAsInt());
        } else {
            System.out.println("No integer starting with 1 was found.");
        }
    }


}
