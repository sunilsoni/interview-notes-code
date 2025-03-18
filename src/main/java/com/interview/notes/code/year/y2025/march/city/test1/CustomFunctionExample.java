package com.interview.notes.code.year.y2025.march.city.test1;

// Custom Functional Interface
@FunctionalInterface
interface StringConverter<T, R> {
    R convert(T input);
}

public class CustomFunctionExample {
    public static void main(String[] args) {
        // Method 1: Creating implementation class
        StringToIntConverter converter = new StringToIntConverter();
        
        // Method 2: Using lambda
        StringConverter<String, Integer> lengthCounter = str -> str.length();

        // Test data
        String testString = "Hello";

        // Using custom implementation
        System.out.println("String length using custom class: " + 
            converter.convert(testString));  // Output: 5

        // Using lambda implementation
        System.out.println("String length using lambda: " + 
            lengthCounter.convert(testString));  // Output: 5

        // Another example with different conversion
        StringConverter<String, String> toUpperCase = str -> str.toUpperCase();
        System.out.println("Uppercase conversion: " + 
            toUpperCase.convert(testString));  // Output: HELLO
    }
}

// Custom Implementation class
class StringToIntConverter implements StringConverter<String, Integer> {
    @Override
    public Integer convert(String input) {
        return input.length();
    }
}
