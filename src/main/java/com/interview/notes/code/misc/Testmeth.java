package com.interview.notes.code.misc;

public class Testmeth {
    static int i = 1;

    public static void main(String args[]) {
        //  System.out.println(i +” , “);//1
        //  m(i);//compile time error
        System.out.println(i);
    }

    public void m(int i) {
        i += 2;
    }
}
