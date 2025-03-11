package com.interview.notes.code.year.y2025.march.Glider.test3;

class TrimString {
    public static void main(String[] args) {
        // Test cases
        System.out.println("\"" + trim(" Mary had a little lamb! ") + "\"");
        System.out.println("\"" + trim("  Hello World!  ") + "\"");
        System.out.println("\"" + trim("NoSpaces") + "\"");
        System.out.println("\"" + trim("  Leading spaces only") + "\"");
        System.out.println("\"" + trim("Trailing spaces only  ") + "\"");
    }

    public static String trim(String input) {
        int s = 0, e = input.length() - 1;

        while (s <= e && input.charAt(s) == ' ') {
            s++;
        }
        while (e >= s && input.charAt(e) == ' ') {
            e--;
        }

        return input.substring(s, e + 1);
    }
}
