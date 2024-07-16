package com.interview.notes.code.months.july24.test6;

import java.util.ArrayList;

// Interface definition for MyList (assuming basic structure as per the description)
interface MyList {
    void convert(String[] a) throws InvalidStringException;

    void replace(int idx) throws InvalidStringException;

    ArrayList<String> compact();
}

// Custom Exception class for handling invalid strings
class InvalidStringException extends Exception {
    public InvalidStringException() {
        super();  // Call the constructor of the parent Exception class
    }

    public InvalidStringException(String message) {
        super(message);  // Call the constructor of the parent Exception class with a message
    }
}

// Class that implements MyList, using an ArrayList to manage strings
class ArrayToList implements MyList {
    private ArrayList<String> arrayToList;  // Class variable for storing strings

    // Constructor
    public ArrayToList() {
        arrayToList = new ArrayList<>();  // Initialize the ArrayList
    }

    // Method to convert array to ArrayList and print additions
    public void convert(String[] a) throws InvalidStringException {
        for (String str : a) {
            if (str == null || str.isEmpty()) {
                throw new InvalidStringException("Invalid string: " + str);
            }
            arrayToList.add(str);
            System.out.println("Added string: " + str);
        }
    }


    // Method to replace a string at a given index with an empty string and print the change
    public void replace(int idx) throws InvalidStringException {
        if (idx < 0 || idx >= arrayToList.size() || arrayToList.get(idx) == null) {
            throw new InvalidStringException("Invalid index or string at index: " + idx);
        }
        arrayToList.set(idx, "");
        System.out.println("Replaced string at index " + idx + " with an empty string.");
    }


    // Method to remove all empty strings from the list and return the compacted list
    public ArrayList<String> compact() {
        ArrayList<String> compacted = new ArrayList<>();
        for (String s : arrayToList) {
            if (!s.isEmpty()) {
                compacted.add(s);
            }
        }
        arrayToList = compacted;  // Optionally update the main list
        return compacted;
    }
}

// Example usage
public class Main {
    public static void main(String[] args) throws InvalidStringException {
        ArrayToList myList = new ArrayToList();
        String[] strings = {"abcd", "1234"};
        myList.convert(strings);  // Adding strings
        myList.replace(1);  // Replacing string at index 1

        // Optional: Demonstrating the compact method
        myList.compact();  // Removing empty strings
    }
}
