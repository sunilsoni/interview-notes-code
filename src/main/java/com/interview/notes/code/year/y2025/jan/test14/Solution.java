package com.interview.notes.code.year.y2025.jan.test14;

public class Solution {
    private static final String DOCUMENT = """
            In publishing and graphic design, lorem ipsum is a
            filler text commonly used to demonstrate the graphic elements of a
            document or visual presentation. Replacing meaningful content that
            could be distracting with placeholder text may allow viewers to focus
            on graphic aspects such as font, typography, and page layout. It also
            reduces the need for the designer to come up with meaningful text, as
            they can instead use hastily generated lorem ipsum text. The lorem
            ipsum text is typically a scrambled section of De finibus bonorum et
            malorum, a 1st-century BC Latin text by Cicero, with words altered,
            added, and removed to make it nonsensical, improper Latin. A variation
            of the ordinary lorem ipsum text has been used in typesetting since
            the 1960s or earlier, when it was popularized by advertisements for
            Letraset transfer sheets. It was introduced to the Information Age in
            the mid-1980s by Aldus Corporation, which employed it in graphics and
            word processing templates for its desktop publishing program,
            PageMaker, for the Apple Macintosh. A common form of lorem ipsum
            reads: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
            minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
            culpa qui officia deserunt mollit anim id est laborum.""".replace('\n', ' ');

    public static double shortestDistance(String document, String word1, String word2) {
        String[] words = document.split(" ");
        double shortest = Double.MAX_VALUE;

        // Store all positions of both words
        java.util.ArrayList<Double> positions1 = new java.util.ArrayList<>();
        java.util.ArrayList<Double> positions2 = new java.util.ArrayList<>();

        double position = 0;
        for (String word : words) {
            // Skip empty words
            if (word.isEmpty()) {
                position += 1;
                continue;
            }

            double midPoint = position + (word.length() / 2.0);

            if (word.equalsIgnoreCase(word1)) {
                positions1.add(midPoint);
            }
            if (word.equalsIgnoreCase(word2)) {
                positions2.add(midPoint);
            }

            position += word.length() + 1;
        }

        // If either word is not found, return -1
        if (positions1.isEmpty() || positions2.isEmpty()) {
            return -1;
        }

        // Find the shortest distance between any pair of positions
        for (double pos1 : positions1) {
            for (double pos2 : positions2) {
                double distance = Math.abs(pos1 - pos2);
                if (distance < shortest) {
                    shortest = distance;
                }
            }
        }

        return shortest;
    }

    public static boolean doTestsPass() {
        return shortestDistance(DOCUMENT, "and", "graphic") == 6d &&
                shortestDistance(DOCUMENT, "transfer", "it") == 14d &&
                shortestDistance(DOCUMENT, "layout", "It") == 6d &&
                shortestDistance(DOCUMENT, "Design", "filler") == 25d &&
                shortestDistance(DOCUMENT, "It", "transfer") == 14d &&
                Math.abs(shortestDistance(DOCUMENT, "of", "lorem") - 4.5) < 0.000001 &&
                shortestDistance(DOCUMENT, "thiswordisnothere", "lorem") == -1d;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
