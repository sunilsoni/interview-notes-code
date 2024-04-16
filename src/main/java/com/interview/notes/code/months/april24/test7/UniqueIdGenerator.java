package com.interview.notes.code.months.april24.test7;

public class UniqueIdGenerator {
    /**
     * Simulates an external library method that fills an array with unique IDs.
     *
     * @param idArray Array to be filled with unique IDs.
     * @param count   Number of unique IDs to generate.
     */
    public static void getIds(int[] idArray, int count) {
        for (int i = 0; i < count; i++) {
            idArray[i] = (int) (Math.random() * 10000);  // Random ID generation for demonstration
        }
    }

    /**
     * Wrapper method to get a single unique ID using the external library.
     *
     * @return A single unique ID.
     */
    public static int getOneId() {
        int[] ids = new int[1];  // Array to store the single ID
        getIds(ids, 1);  // Request the library to fill the array with one ID
        return ids[0];  // Return the generated ID
    }

    public static void main(String[] args) {
        int uniqueId = getOneId();  // Get a unique ID
        System.out.println("Generated Unique ID: " + uniqueId);  // Print the unique ID
    }
}
