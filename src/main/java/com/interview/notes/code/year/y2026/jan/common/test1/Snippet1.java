package com.interview.notes.code.year.y2026.jan.common.test1;

public class Snippet1 {

    public static void main(String[] args) {
        Shape newShape = new Circle();
        System.out.println(newShape);

        newShape = new Triangle();
        System.out.println(newShape);
    }

    private static abstract class Shape {
        @Override
        public String toString() {
            return "I am a Shape";
        }
    }

    private static class Circle extends Shape {
        @Override
        public String toString() {
            return "I am a Circle";
        }
    }

    private static class Triangle extends Shape {
        // inherits toString() from Shape
    }
}
