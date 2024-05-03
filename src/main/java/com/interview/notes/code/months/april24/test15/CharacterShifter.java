package com.interview.notes.code.months.april24.test15;

public class CharacterShifter {

    public static String shiftString(String input, int shift) {
        StringBuilder shifted = new StringBuilder();
        // Normalize shift to be within 0 to 25
        shift = shift % 26;

        for (char c : input.toCharArray()) {
            int shiftedValue = c + shift;
            // Wrap around if the character goes beyond 'z'
            if (shiftedValue > 'z') {
                shiftedValue = shiftedValue - 26;
            }
            shifted.append((char) shiftedValue);
        }
        return shifted.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String test1 = shiftString("abc", 2); // Should return "cde"
        System.out.println("Output for \"abc\" shifted by 2: " + test1);

        String test2 = shiftString("zyx", 3); // Should return "cba"
        System.out.println("Output for \"zyx\" shifted by 3: " + test2);
    }
}
