package com.interview.notes.code.year.y2026.april.common.test8;

public class MultiplyStrings {

    // method to multiply two string numbers
    static String multiply(String num1, String num2) {

        // if any number is "0", result is "0"
        if (num1.equals("0") || num2.equals("0")) return "0";

        // lengths of both numbers
        int n = num1.length(), m = num2.length();

        // result array to store intermediate sums (max size n+m)
        int[] res = new int[n + m];

        // loop from last digit of num1
        for (int i = n - 1; i >= 0; i--) {

            // convert char to digit
            int d1 = num1.charAt(i) - '0';

            // loop from last digit of num2
            for (int j = m - 1; j >= 0; j--) {

                // convert char to digit
                int d2 = num2.charAt(j) - '0';

                // multiply digits
                int mul = d1 * d2;

                // position in result array
                int p1 = i + j;       // carry position
                int p2 = i + j + 1;   // actual digit position

                // add multiplication result to existing value
                int sum = mul + res[p2];

                // store unit digit at p2
                res[p2] = sum % 10;

                // carry goes to p1
                res[p1] += sum / 10;
            }
        }

        // build final string result
        StringBuilder sb = new StringBuilder();

        // skip leading zeros
        for (int num : res) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        // return result string
        return sb.toString();
    }

    // test helper method
    static void test(String name, String num1, String num2, String expected) {

        // call multiply method
        String actual = multiply(num1, num2);

        // compare result
        boolean pass = expected.equals(actual);

        // print PASS/FAIL
        System.out.println(name + " : " + (pass ? "PASS" : "FAIL"));

        // print expected
        System.out.println("Expected: " + expected);

        // print actual
        System.out.println("Actual  : " + actual);

        // spacing
        System.out.println();
    }

    // main method for testing
    public static void main(String[] args) {

        // given examples
        test("Test 1", "678", "11", "7458");
        test("Test 2", "454", "54", "24516");

        // edge cases
        test("Zero Case", "0", "1234", "0");
        test("Single Digit", "9", "9", "81");

        // large numbers
        test("Large", "123456789", "987654321", "121932631112635269");

        // same numbers
        test("Same", "111", "111", "12321");
    }
}