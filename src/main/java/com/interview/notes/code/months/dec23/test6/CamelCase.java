package com.interview.notes.code.months.dec23.test6;

/**
 *
 Java : Camel Case :
 Have the function CamelCase (str) take the str parameter being passed and return it in proper camel case format where the first letter of each word is capitalized (excluding the first letter). The string will only contain letters and some combination of delimiter punctuation characters separating each word.

 For example: if str is "BOB loves-coding" then your program should return the string bobLovesCoding.
 Examples
 Input: "cats AND*Dogs-are Awesome"
 Output: catsAndDogsAreAwesome
 Input: "a b c d-e-f%g"
 Output: aBCDEFG

 */
public class CamelCase {

    public static String CamelCase(String str) {
        // Handle empty string case
        if (str.isEmpty()) {
            return str;
        }

        // Split the string into words based on non-letter characters
        String[] words = str.split("[^a-zA-Z]+");

        // Capitalize the first letter of each word except the first one
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                result.append(words[i].toLowerCase());
            } else {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                result.append(words[i].substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
       String s= "cats AND*Dogs-are Awesome";
        System.out.print(CamelCase(s));
    }
}