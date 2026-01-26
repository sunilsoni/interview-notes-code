package com.interview.notes.code.year.y2026.jan.common.test7;

public class Snippet5 {

    public static void main(String[] args) {

        // Redefine input
        args = new String[]{"TRANSPARENT"};

        Color color = Color.valueOf(args[0]);

        if (color.isGreyScale()) {
            System.out.println("Grey scale color - " + color);
        } else if (!color.isTransparent()) {
            System.out.println("Not transparent - " + color);
        } else {
            System.out.println("Can not print - " + color);
        }
    }

    public enum Color {
        RED,
        GREEN,
        BLUE,
        BLACK,
        WHITE,
        GREY,
        TRANSPARENT;

        public boolean isTransparent() {
            return this == TRANSPARENT;
        }

        public boolean isGreyScale() {
            return this == BLACK || this == WHITE || this == GREY;
        }
    }
}
