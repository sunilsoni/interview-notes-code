package com.interview.notes.code.year.y2025.June.common.test9;

public class OverloadPuzzle {

    public void print(Object o) {
        System.out.println("Object version: " + o);
    }

    public void print(String s) {
        System.out.println("String version: " + s);
    }

    public void print1(Integer i) {
        System.out.println("Integer version: " + i);
    }

    public static void main(String[] args) {
        OverloadPuzzle puzzle = new OverloadPuzzle();

        puzzle.print(null); // Compile-time error: reference to print is ambiguous
    }
}
