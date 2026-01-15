package com.interview.notes.code.year.y2026.jan.common.test1;

public class Snippet2 {

    public static void main(String[] args) {
        try {
            char[] array = {'T', 'E', 'S', 'T'};
            int index = array.length;

            while (index > 0) {
                System.out.println(array.toString());
                // infinite loop: index is never decremented
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        } finally {
            System.out.println("Finally");
        }
    }
}
