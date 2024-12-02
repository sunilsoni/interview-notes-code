package com.interview.notes.code.year.y2023.july23.test5;

public class ReverseWords {
    public static void main1(String[] args) {
        String str = "I am from California";
        String reversedString = reverseWords(str);
        System.out.println("Output: " + reversedString);
    }

    /*
    public static Map<Character,Integer> prepareMap(String str){
        Map<Character,Integer> map = new LinkedHashMap<>();

        for (String word : words) {
            String reversedWord = reverseString(word);
            reversedString.append(reversedWord).append(" ");
        }
    }*/

    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        StringBuilder reversedString = new StringBuilder();

        for (String word : words) {
            String reversedWord = reverseString(word);
            reversedString.append(reversedWord).append(" ");
        }

        return reversedString.toString().trim();
    }

    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    public static void main2(String[] args) {
        String str = "I am from California";
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            words[i] = reversed.substring(0, 1).toUpperCase() + reversed.substring(1).toLowerCase();
        }
        String output = String.join(" ", words);
        System.out.println(output);
    }

    public static void main(String[] args) {
        String str = "I am from California";
        String[] words = str.split(" "); // splitting the string into words
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString(); // reversing each word
        }
        String output = String.join(" ", words); // joining the words back together
        System.out.println(output);
    }
}
