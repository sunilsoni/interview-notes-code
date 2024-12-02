package com.interview.notes.code.year.y2024.may24.test1;

public class FizzBuzzBizz1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            StringBuilder result = new StringBuilder();

            if (i % 3 == 0) {
                result.append("Fizz");
            }
            if (i % 5 == 0) {
                result.append("Buzz");
            }
            if (i % 7 == 0) {
                result.append("Bizz");
            }

            if (result.length() == 0) {  // If no condition was true, append the number itself
                result.append(i);
            }

            System.out.println(result);
        }
    }
}
