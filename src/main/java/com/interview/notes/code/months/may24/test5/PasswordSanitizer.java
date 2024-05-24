package com.interview.notes.code.months.may24.test5;

import java.util.ArrayList;
import java.util.List;

class PasswordSanitizer {

    public static void main(String[] args) {
        List<String> passwords = new ArrayList<String>();
        passwords.add("p@c1");
        passwords.add("pass@123");
        passwords.add("word@321");
        passwords.add("wordpass");
        passwords.add("987345");

        PasswordSanitizer sanitizer = new PasswordSanitizer();
        System.out.println(sanitizer.filter(passwords));
    }

    public String filter(List<String> passwords) {
        List<String> validPasswords = new ArrayList<String>();

        for (String password : passwords) {
            if (isValid(password)) {
                validPasswords.add(password);
            }
        }

        return join(validPasswords, " ");
    }

    private boolean isValid(String password) {
        if (password.length() < 5) {
            return false; // Check if the password is too short
        }
        if (password.matches("^[0-9]+$")) {
            return false; // Check if the password contains only numbers
        }
        if (password.matches("^[a-zA-Z]+$")) {
            return false; // Check if the password contains only letters
        }
        return true; // If none of the above, it's valid
    }

    // Helper method to join strings with a delimiter
    private String join(List<String> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        String loopDelimiter = "";

        for (String s : list) {
            sb.append(loopDelimiter);
            sb.append(s);
            loopDelimiter = delimiter;
        }

        return sb.toString();
    }
}
