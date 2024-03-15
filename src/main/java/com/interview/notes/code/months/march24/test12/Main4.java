package com.interview.notes.code.months.march24.test12;

import java.util.function.Consumer;

//Working
public class Main4 {
    public static void main(String[] args) {
        Consumer<String> processInput = input -> {
            int maxLength = 10;
            StringBuilder resultBuilder = new StringBuilder();

            // Truncate the input string if it's longer than maxLength
            if (input.length() > maxLength) {
                resultBuilder.append(input, 0, maxLength);
            } else {
                // Pad the input string with zeros if it's shorter than maxLength
                resultBuilder.append(input);
                while (resultBuilder.length() < maxLength) {
                    resultBuilder.append("0");
                }
            }

            String result = resultBuilder.toString();
            System.out.println("Input: " + input);
            System.out.println("Output: " + result);
        };

        // Test cases
        processInput.accept("sreedhar");
        processInput.accept("sree");
        processInput.accept("sreedharsss");
    }
}
