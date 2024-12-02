package com.interview.notes.code.year.y2023.dec23.test6;

public class Test6 {


    public static void foo(StringBuffer sb) {
        sb.append("foo");  // Appends "foo" to the original sb
        sb = new StringBuffer();  // Creates a new StringBuffer, sb now refers to this new object
        sb.append("wow");  // Appends "wow" to the new StringBuffer, not affecting sbOriginal
    }

    public static void main(String[] args) {
        StringBuffer sbOriginal = new StringBuffer("SB1");
        foo(sbOriginal);  // Calls foo with sbOriginal
        System.out.println(sbOriginal);  // Prints sbOriginal
    }

}
