package com.interview.notes.code.year.y2025.jan24.test15;

public class Solution1 {

    /**
     * A sample document for testing.  In real life, you can load or generate
     * your own text. This text is the same (or very similar) to that shown
     * in the screenshot.
     */
    private static final String DOCUMENT = ""
        + "In publishing and graphic design, lorem ipsum is a "
        + "filler text commonly used to demonstrate the graphic elements of a "
        + "document or visual presentation. Replacing meaningful content that "
        + "could be distracting with placeholder text may allow viewers to focus "
        + "on graphic aspects such as font, typography, and page layout. It also "
        + "reduces the need for the designer to come up with meaningful text, as "
        + "they can instead use hastily generated lorem ipsum text. The lorem "
        + "ipsum text is typically a scrambled section of De finibus bonorum et "
        + "malorum, a 1st-century BC Latin text by Cicero, with words altered, "
        + "added, and removed to make it nonsensical, improper Latin. A variation "
        + "of the ordinary lorem ipsum text has been used in typesetting since "
        + "the 1960s or earlier, when it was popularized by advertisements for "
        + "Letraset transfer sheets. It was introduced to the Information Age in "
        + "the mid-1980s by Aldus Corporation, which employed it in graphics and "
        + "word processing templates for its desktop publishing program, "
        + "PageMaker, for the Apple Macintosh. A common form of lorem ipsum "
        + "reads: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do "
        + "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad "
        + "minim veniam, quis nostrud exercitation ullamco laboris nisi ut "
        + "aliquip ex ea commodo consequat. Duis aute irure dolor in "
        + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla "
        + "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in "
        + "culpa qui officia deserunt mollit anim id est laborum."
        // The original code replaced newlines with spaces. We'll just keep it as one line:
        .replace('\n', ' ');

    /**
     * Given two words, returns the shortest distance between their midpoints
     * in the DOCUMENT, measured in number of characters.  If either word
     * does not appear, returns -1.  We consider the words in a case-insensitive
     * fashion (e.g., "Design" = "design").
     *
     * This code accounts for:
     *   1) Case insensitivity
     *   2) Handling the situation if word1 or word2 is never found
     *   3) Returning the absolute distance so it is never negative
     */
    public static double shortestDistance(String document, String word1, String word2) {
        // Quick sanity check
        if (document == null || word1 == null || word2 == null) {
            return -1;
        }

        // 1) Convert everything to lower-case for case-insensitive matching
        document = document.toLowerCase();
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        // 2) Split on whitespace so we treat each token as a "word"
        //    Punctuation is retained within tokens, but for the original test
        //    data that usually won't matter (they only tested actual words).
        //String[] words = document.split("\\s+");

        String[] words = document.split("[, .]");

        int index = 0;                 // Tracks the character position in the document
        double shortest = Double.MAX_VALUE; 
        double word1Loc = -1;          // Last midpoint location of word1 (if found)
        double word2Loc = -1;          // Last midpoint location of word2 (if found)

        // Walk through each token and record the midpoint for any occurrences of word1 or word2
        for (String w : words) {
            // The midpoint of this token in the overall text:
            // (index is the start; add half of this token's length)
            double midpoint = index + (w.length() / 2.0);

            // Check if the current token is word1 or word2
            if (w.equals(word1)) {
                word1Loc = midpoint;
            } else if (w.equals(word2)) {
                word2Loc = midpoint;
            }

            // If both words have been encountered at least once,
            // compute distance between their midpoints
            if (word1Loc >= 0 && word2Loc >= 0) {
                double currentDist = Math.abs(word2Loc - word1Loc);
                if (currentDist < shortest) {
                    shortest = currentDist;
                }
            }

            // Advance 'index' by the length of this token plus one for the space
            // (so that distances reflect actual character positions)
            index += w.length() + 1;
        }

        // If we never found one or both words, return -1
        if (shortest == Double.MAX_VALUE) {
            return -1;
        }
        return shortest;
    }

    /**
     * A simple test harness that checks whether all required
     * test-cases pass.  If any fail, returns false.
     */
    public static boolean doTestsPass() {
        return  shortestDistance(DOCUMENT, "and", "graphic") == 6d &&
                shortestDistance(DOCUMENT, "transfer", "it") == 14d &&
                shortestDistance(DOCUMENT, "layout", "It") == 6d &&
                shortestDistance(DOCUMENT, "Design", "filler") == 25d &&
                shortestDistance(DOCUMENT, "It", "transfer") == 14d &&
                // The difference between 4.5 and the actual distance is near zero
                // We'll allow floating-point wiggle room using Math.abs(...)
                Math.abs(shortestDistance(DOCUMENT, "of", "lorem") - 4.5) < 0.000001 &&
                // If a word doesn't exist, we expect -1
                shortestDistance(DOCUMENT, "thiswordisnotthere", "lorem") == -1d;
    }

    /**
     * Main method. Executes the tests and prints the results.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
