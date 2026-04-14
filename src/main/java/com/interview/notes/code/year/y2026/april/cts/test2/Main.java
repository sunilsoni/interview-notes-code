package com.interview.notes.code.year.y2026.april.cts.test2;

public class Main { // main class

    // method to reverse string keeping spaces same
    static String reverseKeepSpace(String s) { // function start
        char[] arr = s.toCharArray(); // convert string to char array for easy swap
        int l = 0, r = arr.length - 1; // two pointers start and end

        while (l < r) { // loop till both pointers meet

            if (arr[l] == ' ') { // if left is space
                l++; // skip left
            } else if (arr[r] == ' ') { // if right is space
                r--; // skip right
            } else { // both are characters
                char t = arr[l]; // store left char
                arr[l] = arr[r]; // swap right to left
                arr[r] = t; // assign temp to right
                l++; // move left forward
                r--; // move right backward
            }
        }

        return new String(arr); // convert array back to string
    }

    // test method to validate PASS/FAIL
    static void test(String input, String expected) { // test helper
        String out = reverseKeepSpace(input); // call function
        System.out.println(
            (out.equals(expected) ? "PASS" : "FAIL") + 
            " | input=" + input + 
            " | output=" + out + 
            " | expected=" + expected
        ); // print result
    }

    public static void main(String[] args) { // main method

        // basic test
        test("example is a test", "tseta si elpmaxe"); // expected manually adjusted

        // edge cases
        test("a b c", "c b a"); // spaces in between
        test("   ", "   "); // only spaces
        test("abc", "cba"); // no spaces
        test("", ""); // empty

        // large input test
        String large = "a".repeat(100000) + " " + "b".repeat(100000); // big string
        String res = reverseKeepSpace(large); // process large
        System.out.println(res.length() == large.length() ? "PASS LARGE" : "FAIL LARGE"); // validate size
    }
}