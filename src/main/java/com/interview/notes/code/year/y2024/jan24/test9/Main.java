package com.interview.notes.code.year.y2024.jan24.test9;

public class Main {
    public static void main(String[] args) {
        String s = "a"; // Creates a new String object with value "a"
        changeme(s); // Calls the changeme method with the string "a"
        s += "c"; // Concatenates "c" to the original value of s
        System.out.println(s); // Prints the value of s to the console
    }

    static String changeme(String s) {
        s = s + "b"; // Concatenates "b" to the original value of s
        return s; // Returns the modified string
    }
}
