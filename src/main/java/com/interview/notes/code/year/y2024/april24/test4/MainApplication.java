package com.interview.notes.code.year.y2024.april24.test4;

/**
 * The exercise outlined in our discussion seems to be centered around creating a memory-efficient data storage system for a set of records, with a focus on optimizing the representation of location data. The primary objective is to manage a collection of records in a way that minimizes memory usage while still allowing for efficient data retrieval and manipulation. This scenario could be framed as a coding exercise or interview question as follows:
 * <p>
 * ### Coding Exercise: Memory-Efficient Data Storage System
 * <p>
 * **Objective:**
 * Design and implement a Java-based system that efficiently stores and manages a collection of records. Each record contains three fields:
 * - An integer ID that uniquely identifies a person.
 * - A salary represented as a float.
 * - A location represented as a string in the format "City, State".
 * <p>
 * **Requirements:**
 * <p>
 * 1. **Data Structure Design:**
 * - Implement a class named `Table` that can store these records in memory.
 * - Focus on memory efficiency, especially regarding the storage of location strings.
 * <p>
 * 2. **Functionality:**
 * - The `Table` class should provide an `insert` method to add new records.
 * - Implement a `get` method that retrieves all records associated with a given ID, displaying them in a human-readable format.
 * <p>
 * 3. **Optimization Goal:**
 * - Given that location strings can significantly impact memory usage, devise a strategy to reduce the memory footprint of storing these strings. Consider the trade-offs of using direct string storage versus alternative approaches like enumeration, integer encoding, or other compression techniques.
 * <p>
 * 4. **Memory Efficiency Consideration:**
 * - Assume the system runs in a constrained memory environment. Detail any assumptions and justify your design choices in terms of their impact on memory usage.
 * <p>
 * 5. **Code Clarity and Organization:**
 * - Your implementation should be clear and well-organized, with comments explaining the rationale behind your design and coding choices.
 * <p>
 * 6. **Bonus - Scalability and Flexibility:**
 * - Consider how your system could be extended to accommodate a dynamic set of locations without requiring significant rework or compromising memory efficiency.
 * <p>
 * **Deliverables:**
 * <p>
 * - Java code for the `Table` class and any additional classes or enums you create to support the design.
 * - A `main` method demonstrating the insertion of records, retrieval of data, and an overview of how location data is managed efficiently.
 * - A brief explanation of your approach, focusing on how it achieves memory efficiency and any limitations or future improvements that could be made.
 * <p>
 * ---
 * <p>
 * This exercise tests a candidate's ability to design efficient data structures, manage memory in Java, and apply optimization techniques to reduce the memory footprint of an application. It also assesses the candidate's understanding of Java's data types, collections framework, and object-oriented programming principles.
 */
public class MainApplication {
    public static void main(String[] args) {
        Table table = new Table();

        table.insert(1, 55000.00f, "Boston, MA");
        table.insert(2, 62000.00f, "New York, NY");
        table.insert(1, 57000.00f, "Boston, MA");
        table.insert(3, 50000.00f, "Chicago, IL");

        System.out.println("Records for ID 1:");
        table.getRecordsAsString(1).forEach(System.out::println);

        System.out.println("\nAll unique locations:");
        table.getAllLocations().forEach((id, location) ->
                System.out.println("ID: " + id + ", Location: " + location)
        );
    }
}