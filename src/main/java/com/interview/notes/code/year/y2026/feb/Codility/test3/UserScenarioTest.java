package com.interview.notes.code.year.y2026.feb.Codility.test3;

public class UserScenarioTest { // A simple class to run your exact 1A, 1B, 2A test case

    public static void main(String[] args) { // Using the main method for simple testing as requested
        
        var map = new UniqueValueMapTest.UniqueValueMap<Integer, String>(); // Initializes our custom map with Integer keys and String values
        
        System.out.println("--- Starting Test ---"); // Prints a header for clean console output
        
        boolean insert1A = map.put(1, "A"); // Attempts to add Value "A" under Key 1
        
        System.out.println("Inserting 1A Allowed? " + insert1A); // Prints 'true' because "A" is completely new to the map
        
        boolean insert1B = map.put(1, "B"); // Attempts to add Value "B" under the SAME Key 1
        
        System.out.println("Inserting 1B Allowed? " + insert1B); // Prints 'true' because duplicate keys are allowed, and "B" is uniquely new
        
        boolean insert2A = map.put(2, "A"); // Attempts to add Value "A" under a NEW Key 2
        
        System.out.println("Inserting 2A Allowed? " + insert2A); // Prints 'false' and blocks it, because "A" is globally locked by Key 1
        
        System.out.println("--- Final State ---"); // Prints a separator to show the final data state
        
        System.out.println("Key 1 contains: " + map.get(1)); // Retrieves Key 1's list; it will print [A, B]
        
        System.out.println("Key 2 contains: " + map.get(2)); // Retrieves Key 2's list; it will print [] (an empty list, proving 2A failed)
        
    } // Closes the main method
    
} // Closes the test class