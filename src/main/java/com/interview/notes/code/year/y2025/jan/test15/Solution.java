package com.interview.notes.code.year.y2025.jan.test15;

public class Solution {

    /**
     * A sample document for testing (same as before).
     * Make sure it matches exactly the text the tests expect.
     */
    private static final String DOCUMENT = "In publishing and graphic design, lorem ipsum is a "
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
            .replace('\n', ' ');

    /**
     * Returns the shortest distance (in characters) between the midpoints of two words,
     * ignoring case.
     * <p>
     * - We iterate character-by-character through 'document'.
     * - 'index' counts every character (including punctuation/spaces).
     * - We build "words" only from [a-z0-9], ignoring punctuation for the word's length.
     * - If either word doesn't appear, return -1.
     */
    public static double shortestDistance(String document, String word1, String word2) {
        if (document == null || word1 == null || word2 == null) {
            return -1;
        }

        // Convert search words to lowercase (for case-insensitive matching).
        // We'll also process the document in lowercase on the fly.
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        double shortest = Double.MAX_VALUE;
        double word1Mid = -1;
        double word2Mid = -1;

        // These track the start index of the current word in the overall text,
        // and the length of the current word (letters/digits only).
        int wordStartIndex = -1;   // where in the *character stream* this word began
        int wordLength = 0;       // how many alphanumeric chars are in this word

        // We'll walk through the document character-by-character
        // 'i' is the overall index in the text (including punctuation/spaces).
        // This is how we ensure punctuation/spaces shift the positions.
        for (int i = 0; i < document.length(); i++) {
            char c = document.charAt(i);
            // Convert to lowercase
            char lower = Character.toLowerCase(c);

            // Check if it's alphanumeric
            boolean isAlnum = (lower >= 'a' && lower <= 'z')
                    || (lower >= '0' && lower <= '9');

            if (isAlnum) {
                // If we're not currently "in a word," mark the start now
                if (wordStartIndex < 0) {
                    wordStartIndex = i;
                    wordLength = 1;
                } else {
                    // We're continuing the same word
                    wordLength++;
                }
            } else {
                // It's punctuation or whitespace
                // => If we *were* in a word, that word just ended
                if (wordStartIndex >= 0) {
                    // Compute that word's midpoint in the *character stream*
                    double midpoint = wordStartIndex + (wordLength / 2.0);

                    // Extract the actual word from document[wordStartIndex .. wordStartIndex + wordLength]
                    String theWord = document.substring(wordStartIndex, wordStartIndex + wordLength);
                    theWord = theWord.toLowerCase(); // for case-insensitive compare

                    // Check if it matches word1 or word2
                    if (theWord.equals(word1)) {
                        word1Mid = midpoint;
                    } else if (theWord.equals(word2)) {
                        word2Mid = midpoint;
                    }

                    // If both have been found at least once, update shortest distance
                    if (word1Mid >= 0 && word2Mid >= 0) {
                        double dist = Math.abs(word2Mid - word1Mid);
                        if (dist < shortest) {
                            shortest = dist;
                        }
                    }

                    // Reset so we can detect the next word
                    wordStartIndex = -1;
                    wordLength = 0;
                }
                // If we weren't in a word, we just keep going
            }
        }

        // Edge case: if the document ends with a letter, there's one last word to process
        if (wordStartIndex >= 0) {
            double midpoint = wordStartIndex + (wordLength / 2.0);
            String theWord = document.substring(wordStartIndex, wordStartIndex + wordLength).toLowerCase();
            if (theWord.equals(word1)) {
                word1Mid = midpoint;
            } else if (theWord.equals(word2)) {
                word2Mid = midpoint;
            }
            if (word1Mid >= 0 && word2Mid >= 0) {
                double dist = Math.abs(word2Mid - word1Mid);
                if (dist < shortest) {
                    shortest = dist;
                }
            }
        }

        // If one or both words never appeared, shortest is still Double.MAX_VALUE
        return (shortest == Double.MAX_VALUE) ? -1 : shortest;
    }

    /**
     * Tests from your screenshot, including the failing "layout" vs. "It" check.
     */
    public static boolean doTestsPass() {
        return shortestDistance(DOCUMENT, "and", "graphic") == 6d &&
                shortestDistance(DOCUMENT, "transfer", "it") == 14d &&
                shortestDistance(DOCUMENT, "layout", "It") == 6d &&  // This was failing before
                shortestDistance(DOCUMENT, "Design", "filler") == 25d &&
                shortestDistance(DOCUMENT, "It", "transfer") == 14d &&
                Math.abs(shortestDistance(DOCUMENT, "of", "lorem") - 4.5) < 0.000001 &&
                shortestDistance(DOCUMENT, "thiswordisnotthere", "lorem") == -1d;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass!");
        } else {
            System.out.println("Test failures detected.");
            // Print out actual results for debugging
            System.out.println("distance(and, graphic)    = " + shortestDistance(DOCUMENT, "and", "graphic"));
            System.out.println("distance(transfer, it)    = " + shortestDistance(DOCUMENT, "transfer", "it"));
            System.out.println("distance(layout, It)      = " + shortestDistance(DOCUMENT, "layout", "It"));
            System.out.println("distance(Design, filler)  = " + shortestDistance(DOCUMENT, "Design", "filler"));
            System.out.println("distance(It, transfer)    = " + shortestDistance(DOCUMENT, "It", "transfer"));
            System.out.println("distance(of, lorem)       = " + shortestDistance(DOCUMENT, "of", "lorem"));
            System.out.println("distance(nope, lorem)     = " + shortestDistance(DOCUMENT, "thiswordisnotthere", "lorem"));
        }
    }
}
