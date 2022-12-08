package com.interview.notes.code.misc;

/**
 *
 "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing."

 Write a method that, given a sentence like the one above, along with the position of an opening parenthesis, finds the corresponding closing parenthesis.
 Example: if the example string above is input with the number 10 (position of the first parenthesis), the output should be 79 (position of the last parenthesis).


 */
public class Parenthesis {
    public static void main(String[] args) {
        String s =  "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";
        findClosingParen(s.toCharArray(),10);
    }

    public static int findClosingParen(char[] text, int openPos) {
        int closePos = openPos;
        int counter = 1;
        while (counter > 0) {
            char c = text[++closePos];

            if(closePos==79){
                String s="a";
            }

            if (c == '(') {
                counter++;
            }
            else if (c == ')') {
                counter--;
            }
        }
        return closePos;
    }
}
