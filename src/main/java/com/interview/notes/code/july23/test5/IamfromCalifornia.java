package com.interview.notes.code.july23.test5;


public class IamfromCalifornia {


    public static void main(String[] args) {
        String str = "I am from California";
        StringBuilder reversedString = new StringBuilder(str);
        StringBuilder sb = new StringBuilder();
        reversedString = reversedString.reverse();
        String strWithoutSpaces = str.replaceAll("\\s", "");

        String reversedStr = reversedString.toString().replaceAll("\\s", "");
        String[] words = str.split(" ");
        System.out.println("words: " + words.length);
        for (String word : words) {
            int index1 = strWithoutSpaces.indexOf(word);
            int index2 = word.length() + index1;
            sb.append(reversedStr.substring(index1, index2));
            sb.append(" ");
        }

        System.out.println("sb string: " + sb);//Output = "a in rofi laCmorfmaI"
    }

    public static void main1(String[] args) {

        String sl = "1";
        Integer i = Integer.parseInt(sl);
        Float f = Float.valueOf(i.intValue()); //line 3
        System.out.println(f);

        System.out.println("Test");
    }

    public static void main3(String[] args) {
        String str = "I am from California";
        //String result = removeSpacesAndReverse(str);
        System.out.println("Original string: " + str);


        StringBuilder reversedString = new StringBuilder(str);
        StringBuilder sb = new StringBuilder();
        reversedString = reversedString.reverse();
        System.out.println("reversedString string: " + reversedString);


        String strWithoutSpaces = str.replaceAll("\\s", "");

        String reversedStr = reversedString.toString().replaceAll("\\s", "");
        System.out.println("Original string: " + str);
        System.out.println("String without spaces: " + strWithoutSpaces);

        String[] words = str.split(" ");
        System.out.println("words: " + words.length);
        for (String word : words) {

            int index1 = strWithoutSpaces.indexOf(word);
            int index2 = word.length() + index1;

            System.out.println("word: " + word + " index1: " + index1 + " : index2: " + index2);

            sb.append(reversedStr.substring(index1, index2));
            sb.append(" ");

        }

        System.out.println("sb string: " + sb);//Output = "a in rofi laCmorfmaI"
    }


    public static String removeSpacesAndReverse(String str) {
        // Step 1: Remove spaces from the string
        String stringWithoutSpaces = str.replaceAll("\\s", "");

        // Step 2: Reverse the resulting string
        StringBuilder reversedString = new StringBuilder(stringWithoutSpaces);
        reversedString.reverse();

        return reversedString.toString();
    }
}
