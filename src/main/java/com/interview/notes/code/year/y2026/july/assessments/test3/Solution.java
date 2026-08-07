package com.interview.notes.code.year.y2026.july.assessments.test3;

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
            culpa qui officia deserunt mollit anim id est laborum.
            """.replace('\n', ' ');

    public static double shortestDistance(
            String document, String word1, String word2) {

        String[] words = document.split("[^A-Za-z0-9-]+");

        int from = 0;
        double word1Loc = -1;
        double word2Loc = -1;
        double shortest = Double.MAX_VALUE;

        for (String word : words) {
            int start = document.indexOf(word, from);
            double midpoint = start + word.length() / 2.0;

            if (word.equalsIgnoreCase(word1)) {
                word1Loc = midpoint;

                if (word2Loc >= 0)
                    shortest = Math.min(
                            shortest,
                            Math.abs(word1Loc - word2Loc)
                    );

            } else if (word.equalsIgnoreCase(word2)) {
                word2Loc = midpoint;

                if (word1Loc >= 0)
                    shortest = Math.min(
                            shortest,
                            Math.abs(word1Loc - word2Loc)
                    );
            }

            from = start + word.length();
        }

        return shortest == Double.MAX_VALUE ? -1 : shortest;
    }

    static void test(String word1, String word2, double expected) {
        double actual = shortestDistance(DOCUMENT, word1, word2);

        System.out.printf(
                "%-20s Expected: %-5s Actual: %-5s %s%n",
                word1 + " - " + word2,
                expected,
                actual,
                Math.abs(expected - actual) < 0.000001
                        ? "PASS"
                        : "FAIL"
        );
    }

    public static void main(String[] args) {
        test("and", "graphic", 6);
        test("transfer", "it", 14);
        test("layout", "It", 6);
        test("Design", "filler", 25);
        test("It", "transfer", 14);
        test("of", "lorem", 4.5);
        test("thiswordisnotthere", "lorem", -1);
    }
}