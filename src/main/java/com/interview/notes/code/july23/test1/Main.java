package com.interview.notes.code.july23.test1;

@FunctionalInterface
interface StringManipulator {
    String manipulate(String input);
    
    static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    
    static String toUpperCase(String input) {
        return input.toUpperCase();
    }
}

public class Main {
    public static void main(String[] args) {
        // Using lambda expressions to implement the abstract method
        StringManipulator addExclamation = (input) -> input + "!";
        StringManipulator removeSpaces = (input) -> input.replaceAll(" ", "");
        
        String originalText = "Hello, world";
        
        System.out.println("Original: " + originalText);
        System.out.println("Exclamation: " + addExclamation.manipulate(originalText));
        System.out.println("No Spaces: " + removeSpaces.manipulate(originalText));
        
        // Using default method and static method
        StringManipulator reverser = StringManipulator::reverse;
        StringManipulator upperCaser = StringManipulator::toUpperCase;
        
        System.out.println("Reversed: " + reverser.manipulate(originalText));
        System.out.println("Uppercase: " + upperCaser.manipulate(originalText));
    }
}
