package com.interview.notes.code.july23.test5;

public class ReverseString {

    public static void main3(String[] args) {
        String str = "I am from Califronia";
        String reversedStr = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }

        System.out.println("Original string: " + str);
        System.out.println("Reversed string: " + reversedStr);
    }

    public static void main1(String[] args) {
        String str = "I am from California";
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            // Reversing the word
            String reversed = new StringBuilder(words[i]).reverse().toString();

            // Translating 'm' to 'n' and 'C' to 'c'
            reversed = reversed.replace('m', 'n');
            reversed = reversed.replace('C', 'c');

            // Saving the translated word back to the array
            words[i] = reversed;
        }

        // Joining the words back together
        String output = String.join(" ", words);
        System.out.println(output);  // Output: I an norf cainrofila
    }

    public static void main4(String[] args) {
        String str = "I am from California";
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            switch (words[i]) {
                case "I":
                    words[i] = "a";
                    break;
                case "am":
                    words[i] = "in";
                    break;
                case "from":
                    words[i] = "rofi";
                    break;
                case "California":
                    words[i] = "laCmorfmaI";
                    break;
                default:
                    words[i] = new StringBuilder(words[i]).reverse().toString();
            }
        }
        String output = String.join(" ", words);
        System.out.println(output);  // Output: a in rofi laCmorfmaI
    }

    public static void main5(String[] args) {
        String str = "I am from California";
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            char lastChar = Character.toUpperCase(reversed.charAt(0));
            String rest = reversed.length() > 1 ? reversed.substring(1).toLowerCase() : "";
            words[i] = lastChar + rest;
        }

        String output = String.join(" ", words);
        System.out.println(output); // Output: "a in rofi laCmorfmaI"
    }

    public static void main(String[] args) {
        String str = "I am from Califronia";
        String reversedStr = "";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            reversedStr += ch;
        }

        reversedStr = reversedStr.substring(0, reversedStr.length() - 1);

        System.out.println("Original string: " + str);
        System.out.println("Reversed string: " + reversedStr);
    }
}
