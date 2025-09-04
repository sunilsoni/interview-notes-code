package com.interview.notes.code.year.y2025.september.common.test;

public class Interview {
    public static void main (String[] args) {
        try {
            System.out.println("try called");
            return;
        } catch (Exception e) {
            System.out.println("Exception called");
            return;
        } finally {
            System.out.println("finally called");
        }
      //  System.out.println("Completed");
    }
}
