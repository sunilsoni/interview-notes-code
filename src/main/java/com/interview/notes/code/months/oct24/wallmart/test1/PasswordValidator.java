package com.interview.notes.code.months.oct24.wallmart.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*


You are working on an authentication system and there is a set of rules the users have to follow when picking a new password:
1. It has to be at least 16 characters long.
2. The password cannot contain the word "password". This rule is not case-sensitive.
3. The same character cannot be used more than 4 times. This rule is case-sensitive, "a" and "A" are different characters.
4. The password has to contain at least one uppercase and one lowercase letter.
5. The password has to contain at least one of the following special characters "*"
1, "#", "@".
Write a function that takes in a password and returns a collection of any rule numbers that are not met.
password_1 = "Strongpwd9999#abc"
==> []

password_2 = "Aess10#"
==> [1]

password_3 = "Password@"

==> [1,2]
password_4 = "#PassWord011111112222223x"

==> [2,3]
password_5 = "PASSWORDz#1111111"

==> [2,3]
password_6 = "aaaapassword$$"

==> [1,2,3,4,5]
password_7 = "LESS10#"

==> [1,4]
password_8 = "SsSSSt#passWord"

==> [1,2]
password_9 = "SsSSSt#passWordpassword"

==> [2,3]
password_10 = "aZ*"

==> [11
validate(password_1) ==> []

validate(password_2) ==> [1]

validate(password_3) ==> [1,2]

validate(password_4) ==> [2,3]

validate(password_5) ==> [2,3]



All test cases:
validate(password_6) ==> [1,2,3,4, 5]


All test cases:
validate(password_1) ==> [] validate(password_2) ==> [1]
validate(password_3) ==> [1,2] validate(password_4) ==> [2,3] validate(password_5) ==> [2,3]
validate(password_6) ==> [1,2,3,4,5]
validate(password_7) ==> [1,4] validate(password_8) ==> [1,2] validate(password_9) ==> [2,3] validate(password_10) ==> [1]
Complexity variables:
N = length of the password
 */
public class PasswordValidator {

    // Main validation function
    public static List<Integer> validate(String password) {
        List<Integer> violations = new ArrayList<>();

        // Rule 1: Password must be at least 16 characters long
        if (password.length() < 16) {
            violations.add(1);
        }

        // Rule 2: Password must not contain "password" (case-insensitive)
        if (password.toLowerCase().contains("password")) {
            violations.add(2);
        }

        // Rule 3: No character can be repeated more than 4 times (case-sensitive)
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : password.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (int count : charCount.values()) {
            if (count > 4) {
                violations.add(3);
                break;  // No need to check further once we find a violation
            }
        }

        // Rule 4: Password must contain at least one uppercase and one lowercase letter
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            if (hasUppercase && hasLowercase) {
                break;
            }
        }
        if (!hasUppercase || !hasLowercase) {
            violations.add(4);
        }

        // Rule 5: Password must contain at least one of the special characters "*", "1", "#", "@"
        String specialChars = "*1#@";
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                hasSpecialChar = true;
                break;
            }
        }
        if (!hasSpecialChar) {
            violations.add(5);
        }

        return violations;
    }

    // Test cases
    public static void main(String[] args) {
        runTests();
    }

    // Test method to verify all test cases
    public static void runTests() {
        test("Strongpwd9999#abc", new ArrayList<>());  // []
        test("Aess10#", List.of(1));  // [1]
        test("Password@", List.of(1, 2));  // [1, 2]
        test("#PassWord011111112222223x", List.of(2, 3));  // [2, 3]
        test("PASSWORDz#1111111", List.of(2, 3));  // [2, 3]
        test("aaaapassword$", List.of(1, 2, 3, 4, 5));  // [1, 2, 3, 4, 5]
        test("LESS10#", List.of(1, 4));  // [1, 4]
        test("SsSSSt#passWord", List.of(1, 2));  // [1, 2]
        test("SsSSSt#passWordpassword", List.of(2, 3));  // [2, 3]
        test("aZ*", List.of(1));  // [1]
    }

    // Helper method to run individual test cases
    public static void test(String password, List<Integer> expected) {
        List<Integer> result = validate(password);
        if (result.equals(expected)) {
            System.out.println("PASS for password: " + password);
        } else {
            System.out.println("FAIL for password: " + password + ". Expected: " + expected + ", Got: " + result);
        }
    }
}
