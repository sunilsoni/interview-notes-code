package com.interview.notes.code.year.y2025.august.common.test4;

class Solution {
    private static class PangramDetector {
        // We only ever care about these 26 letters, in order.
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

        /**
         * Finds which lowercase letters 'a'–'z' are missing from the input sentence.
         *
         * @param sentence the input text to examine
         * @return a string of missing lowercase letters, in sorted order
         */
        public String findMissingLetters(String sentence) {
            // Step 1: Turn the sentence into lowercase and get its character stream.
            // This makes 'A' the same as 'a' and lets us process one char at a time.
            // We then filter to include only ASCII letters a–z.
            java.util.Set<Character> present = sentence
                .toLowerCase()                                       // normalize to lowercase
                .chars()                                             // stream of int code-points
                .filter(c -> c >= 'a' && c <= 'z')                  // only keep a–z
                .mapToObj(c -> (char) c)                             // convert int → Character
                .collect(java.util.stream.Collectors.toSet());       // collect distinct

            // Step 2: Stream over our fixed alphabet, keep letters NOT in 'present'.
            // Then join them into one string to return.
            return ALPHABET
                .chars()                                             // a stream of 'a'–'z'
                .filter(c -> !present.contains((char) c))            // only missing ones
                .mapToObj(c -> String.valueOf((char) c))             // convert to String
                .collect(java.util.stream.Collectors.joining());     // concatenate
        }
    }

    /**
     * A simple main method that runs a suite of test cases (including a large input)
     * and prints PASS or FAIL for each one.
     */
    public static void main(String[] args) {
        // Instantiate our detector
        PangramDetector pd = new PangramDetector();

        // Map of test inputs to their expected outputs
        java.util.Map<String, String> tests = new java.util.LinkedHashMap<>();
        tests.put("The quick brown fox jumps over the lazy dog",                                  "");                    // perfect pangram
        tests.put("The slow purple oryx meanders past the quiescent canine",                     "bfgjkvz");             // missing letters
        tests.put("We hates Bagginses!",                                                          "cdfjklmopruvwxyz");   // missing letters
        tests.put("",                                                                             "abcdefghijklmnopqrstuvwxyz"); // empty input

        // Large input test: repeat a pangram many times
        StringBuilder largeSb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {       // build a ~4.4 million-char string
            largeSb.append("The quick brown fox jumps over the lazy dog ");
        }
        tests.put(largeSb.toString(), "");        // still a pangram

        // Run each test and print PASS/FAIL
        int testNum = 1;
        for (java.util.Map.Entry<String, String> entry : tests.entrySet()) {
            String input    = entry.getKey();      // test sentence
            String expected = entry.getValue();    // expected missing letters
            String actual   = pd.findMissingLetters(input);

            // Compare and report
            if (expected.equals(actual)) {
                System.out.println("Test " + testNum + ": PASS");
            } else {
                System.out.println("Test " + testNum + ": FAIL"
                    + " | expected='" + expected + "'"
                    + " actual='"   + actual   + "'");
            }
            testNum++;
        }
    }
}