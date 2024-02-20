package com.interview.notes.code.months.feb24.test2;

import java.util.HashSet;
import java.util.Random;

public class UniqueIDGenerator {

    // Define the character set to use for ID generation. It includes lowercase letters (a-z),
    // uppercase letters (A-Z), and numbers (0-9).
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Set the desired length of the unique IDs.
    private static final int ID_LENGTH = 6;
    // Use a HashSet to store already generated IDs to ensure uniqueness.
    private static HashSet<String> generatedIDs = new HashSet<>();
    // Random object to help with generating random indexes for selecting characters.
    private static Random random = new Random();

    /**
     * Generates a unique ID consisting of 6 characters.
     * It randomly selects characters from the defined CHAR_SET until a unique combination is created.
     *
     * @return A unique ID string
     */
    public static String generateUniqueID() {
        StringBuilder idBuilder;
        // Loop until a unique ID is generated
        do {
            // Initialize a StringBuilder to build the ID character by character.
            idBuilder = new StringBuilder(ID_LENGTH);
            for (int i = 0; i < ID_LENGTH; i++) {
                // Generate a random index and append the character at that index from CHAR_SET to the builder.
                int index = random.nextInt(CHAR_SET.length());
                idBuilder.append(CHAR_SET.charAt(index));
            }
            // Check if the generated ID is already in the set of generated IDs. If so, repeat the process.
        } while (generatedIDs.contains(idBuilder.toString()));

        // Convert the StringBuilder to a String, which will be our unique ID.
        String uniqueID = idBuilder.toString();
        // Add the new unique ID to the set to keep track of generated IDs.
        generatedIDs.add(uniqueID);
        // Return the generated unique ID.
        return uniqueID;
    }

    public static void main(String[] args) {
        // Example usage of the generateUniqueID method.
        // Generate and print 10 unique IDs to demonstrate functionality.
        for (int i = 0; i < 10; i++) {
            System.out.println(generateUniqueID());
        }
    }
}
