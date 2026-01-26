package com.interview.notes.code.year.y2026.jan.common.test7;

public class Snippet1 {

    public static void main(String[] args) {

        Shape newShape = new Circle();
        System.out.println(newShape);

        newShape = new Triangle();
        System.out.println(newShape);
    }

    // Abstract parent class
    private static abstract class Shape {
        @Override
        public String toString() {
            return "I am a Shape";
        }
    }

    // Child class overriding toString()
    private static class Circle extends Shape {
        @Override
        public String toString() {
            return "I am a Circle";
        }
    }

    // Child class NOT overriding toString()
    private static class Triangle extends Shape {
        // inherits toString() from Shape
    }
}
