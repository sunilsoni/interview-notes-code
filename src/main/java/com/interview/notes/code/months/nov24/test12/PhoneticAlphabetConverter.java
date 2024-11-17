package com.interview.notes.code.months.nov24.test12;

import java.util.*;

public class PhoneticAlphabetConverter {
    
    // Mappings for the 1908 version
    private static final Map<String, Character> phonetic1908 = new HashMap<>();
    private static final Map<Character, String> alphabet1908 = new HashMap<>();
    
    // Mappings for the 1917 version
    private static final Map<String, Character> phonetic1917 = new HashMap<>();
    private static final Map<Character, String> alphabet1917 = new HashMap<>();
    
    // Mappings for the 1927 version
    private static final Map<String, Character> phonetic1927 = new HashMap<>();
    private static final Map<Character, String> alphabet1927 = new HashMap<>();
    
    // Mappings for the 1956 version (NATO)
    private static final Map<String, Character> phonetic1956 = new HashMap<>();
    private static final Map<Character, String> alphabet1956 = new HashMap<>();
    
    static {
        // Initialize 1908 phonetic alphabet
        String[] words1908 = {"Authority", "Bills", "Capture", "Destroy", "Englishmen", "Fractious", "Galloping", 
                              "High", "Invariably", "Juggling", "Knights", "Loose", "Managing", "Never", "Owners", 
                              "Play", "Queen", "Remarks", "Support", "The", "Unless", "Vindictive", "When", 
                              "Xpeditiously", "Your", "Zigzag"};
        initializeMapping(words1908, phonetic1908, alphabet1908);

        // Initialize 1917 phonetic alphabet
        String[] words1917 = {"Apples", "Butter", "Charlie", "Duff", "Edward", "Freddy", "George", "Harry", 
                              "Ink", "Johnnie", "King", "London", "Monkey", "Nuts", "Orange", "Pudding", "Queenie", 
                              "Robert", "Sugar", "Tommy", "Uncle", "Vinegar", "Willie", "Xerxes", "Yellow", "Zebra"};
        initializeMapping(words1917, phonetic1917, alphabet1917);

        // Initialize 1927 phonetic alphabet
        String[] words1927 = {"Amsterdam", "Baltimore", "Casablanca", "Denmark", "Edison", "Florida", "Gallipoli", 
                              "Havana", "Italia", "Jerusalem", "Kilogramme", "Liverpool", "Madagascar", "New-York", 
                              "Oslo", "Paris", "Quebec", "Roma", "Santiago", "Tripoli", "Uppsala", "Valencia", 
                              "Washington", "Xanthippe", "Yokohama", "Zurich"};
        initializeMapping(words1927, phonetic1927, alphabet1927);

        // Initialize 1956 phonetic alphabet (NATO)
        String[] words1956 = {"Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", 
                              "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", 
                              "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu"};
        initializeMapping(words1956, phonetic1956, alphabet1956);
    }
    
    // Helper method to initialize mappings
    private static void initializeMapping(String[] words, Map<String, Character> phonetic, Map<Character, String> alphabet) {
        char letter = 'A';
        for (String word : words) {
            phonetic.put(word, letter);
            alphabet.put(letter, word);
            letter++;
        }
    }

    // Method to convert the input using the required alphabet version
    public static String convertPhonetic(String input, Map<String, Character> current, Map<Character, String> next) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split(" ");
        for (String word : words) {
            if (current.containsKey(word)) {
                char letter = current.get(word);
                result.append(next.get(letter)).append(" ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Test Case 1:");
        String input1 = "Authority Bills Capture";
        System.out.println("Input: " + input1);
        System.out.println("Output: " + convertPhonetic(input1, phonetic1908, alphabet1917)); // Should convert to 1917

        System.out.println("\nTest Case 2:");
        String input2 = "Apples Butter Charlie";
        System.out.println("Input: " + input2);
        System.out.println("Output: " + convertPhonetic(input2, phonetic1917, alphabet1927)); // Should convert to 1927

        System.out.println("\nTest Case 3:");
        String input3 = "Amsterdam Baltimore Casablanca";
        System.out.println("Input: " + input3);
        System.out.println("Output: " + convertPhonetic(input3, phonetic1927, alphabet1956)); // Should convert to 1956

        System.out.println("\nTest Case 4:");
        String input4 = "Alfa Bravo Charlie";
        System.out.println("Input: " + input4);
        System.out.println("Output: " + convertPhonetic(input4, phonetic1956, alphabet1908)); // Should convert to 1908
    }
}
