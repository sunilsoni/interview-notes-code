package com.interview.notes.code.tricky;

public class Permutations {
    public static void permute(String str, String prefix) {
        int n = str.length();
        if (prefix.length() == 3) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(str.substring(0, i) + str.substring(i + 1, n), prefix + str.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        // example usage
        String input = "abc";
        permute(input, "");
    }

}
