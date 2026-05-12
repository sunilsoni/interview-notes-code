package com.interview.notes.code.year.y2026.may.common.test5;

import java.util.Arrays; // Needed for Arrays.asList in testing
import java.util.List; // Needed for the List interface return type
import java.util.function.BiFunction; // Needed for test result comparison logic
import java.util.stream.IntStream; // Needed to utilize modern Java Stream API for iteration

class Solution { // Main wrapper class for the solution

    private static final String DOCUMENT = "In publishing and graphic design, lorem ipsum is a\n" + // Recreating the sample text
            "filler text commonly used to demonstrate the graphic elements of a\n" + // Line 2 of sample text
            "document or visual presentation. Replacing meaningful content that\n" + // Line 3 of sample text
            "could be distracting with placeholder text may allow viewers to focus\n" + // Line 4 of sample text
            "on graphic aspects such as font, typography, and page layout. It also\n" + // Line 5 of sample text
            "reduces the need for the designer to come up with meaningful text, as\n" + // Line 6 of sample text
            "they can instead use hastily generated lorem ipsum text. The lorem\n" + // Line 7 of sample text
            "ipsum text is typically a scrambled section of De finibus bonorum et\n" + // Line 8 of sample text
            "malorum, a 1st-century BC Latin text by Cicero, with words altered,\n" + // Line 9 of sample text
            "added, and removed to make it nonsensical, improper Latin. A variation\n" + // Line 10 of sample text
            "of the ordinary lorem ipsum text has been used in typesetting since\n" + // Line 11 of sample text
            "the 1960s or earlier, when it was popularized by advertisements for\n" + // Line 12 of sample text
            "Letraset transfer sheets. It was introduced to the Information Age in\n" + // Line 13 of sample text
            "the mid-1980s by Aldus Corporation, which employed it in graphics and\n" + // Line 14 of sample text
            "word processing templates for its desktop publishing program, PageMaker, for the Apple Macintosh. A common form of lorem ipsum\n" + // Line 15 of sample text
            "reads: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do\n" + // Line 16 of sample text
            "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\n" + // Line 17 of sample text
            "minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in\n" + // Line 18 of sample text
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla\n" + // Line 19 of sample text
            "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in\n" + // Line 20 of sample text
            "culpa qui officia deserunt mollit anim id est laborum.".replace('\n', ' '); // Final line, applying the replacement logic from the original snippet

    public static void main(String[] args) { // Main execution thread
        doTestsPass(); // Fires the standard test suite
        testLargeData(); // Fires the stress test for memory/speed validation
    } // Ends main

    // ********** Tests **********

    public static void doTestsPass() { // Standard validation method
        MyPrefixSearch prefixSearch = new MyPrefixSearch(DOCUMENT); // Instantiate search system with our text
        BiFunction<List<?>, List<?>, Boolean> resultMatches = (actual, expected) -> expected.equals(actual); // Helper function to compare lists safely

        boolean passed = resultMatches.apply(prefixSearch.findAll("demonstrate"), List.of(80)) // Assert "demonstrate"
                && resultMatches.apply(prefixSearch.findAll("pub"), Arrays.asList(3, 988)) // Assert exact prefix boundary
                && resultMatches.apply(prefixSearch.findAll("publishing"), Arrays.asList(3, 988)) // Assert full word match
                && resultMatches.apply(prefixSearch.findAll("lab"), Arrays.asList(1173, 1263, 1517)) // Assert recurring prefixes
                && resultMatches.apply(prefixSearch.findAll("laborum"), List.of(1517)) // Assert trailing word match
                && resultMatches.apply(prefixSearch.findAll("in"), Arrays.asList(0, 404, 717, 839, 857, 873, 930, 1159, 1334, 1351, 1468)) // Assert frequent short prefixes
                && resultMatches.apply(prefixSearch.findAll("lor"), Arrays.asList(34, 434, 456, 686, 1061, 1080)) // Assert partial word mappings
                && resultMatches.apply(prefixSearch.findAll("l"), Arrays.asList(34, 309, 434, 456, 557, 651, 686, 806, 1061, 1080, 1173, 1263, 1517)) // Assert single char prefix mappings
                && prefixSearch.findAll("").size() == 0 // Assert empty strings return nothing
                && prefixSearch.findAll("hamburger").size() == 0; // Assert missing words return nothing

        if (passed) { // Condition if every check mapped successfully
            System.out.println("All tests pass"); // Print success message to console
        } else { // Condition if any mismatch occurred
            System.out.println("Test failed"); // Print error message to console
        } // Close if block
    } // Close method

    static void testLargeData() { // Stress test to validate optimization
        StringBuilder sb = new StringBuilder(); // Builder avoids heavy memory overhead while concatenating strings
        for (int i = 0; i < 500_000; i++) { // Loop exactly half a million times
            sb.append("javatest "); // Appending a predictable word pattern with spaces
        } // Close loop

        MyPrefixSearch search = new MyPrefixSearch(sb.toString()); // Load the massive document into our algorithm

        long startTime = System.currentTimeMillis(); // Snapshot system time before execution
        List<Integer> result = search.findAll("java"); // Search the massive text for prefix
        long endTime = System.currentTimeMillis(); // Snapshot system time post-execution

        if (result.size() == 500_000) { // Verify we found exactly the half million entries
            System.out.println("Large Data Test PASS - Extracted " + result.size() + " matches in " + (endTime - startTime) + "ms"); // Log success and time cost
        } else { // If math is wrong or algorithm failed
            System.out.println("Large Data Test FAIL - Found: " + result.size()); // Log the failure count
        } // Close if block
    } // Close large data method

    static class MyPrefixSearch { // Static inner class to encapsulate our logic
        String document; // Instance variable to store the source text

        MyPrefixSearch(String document) { // Constructor mapping the text
            this.document = document; // Assigns passed string to instance memory
        } // Ends constructor

        public List<Integer> findAll(String prefix) { // Core method returning matching indices
            if (prefix == null || prefix.isEmpty()) return List.of(); // Guard clause: immediately return empty list for empty/null prefixes

            String lowerDoc = document.toLowerCase(); // Normalizes entire document to lowercase for case-insensitivity
            String lowerPref = prefix.toLowerCase(); // Normalizes prefix to lowercase to match the document

            return IntStream.iterate( // Modern Java 9+ Stream iterator that acts as a while-loop
                    lowerDoc.indexOf(lowerPref), // Initial seed: finds the absolute first occurrence of the prefix
                    i -> i >= 0, // Continuation condition: keep streaming as long as a valid index was found
                    i -> lowerDoc.indexOf(lowerPref, i + 1) // Next step: find the next occurrence starting just after the current one
                ) // Closes IntStream configuration
                .filter(i -> i == 0 || !Character.isLetterOrDigit(lowerDoc.charAt(i - 1))) // Critical logic: only keep indices at the start of the string OR following a space/punctuation
                .boxed() // Transforms primitive 'int' elements into 'Integer' objects for Collections compatibility
                .toList(); // Java 16+ terminal operation to cleanly collect the stream into an immutable List
        } // Ends findAll method
    } // Ends inner class
} // Ends Solution class