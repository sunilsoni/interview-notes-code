package com.interview.notes.code.misc.IKMTest;

interface I1 {
    String name = "NI";
    String sl = "SI";
}

interface I2 extends I1 {
    String name = "N2";
}

class C2 implements I2 {
    public static void main(String[] args) {

        System.out.print(I2.name + ",");
        //System.out.print(I2.s1 + ",");
        System.out.print(((I1) new C2()).name);
    }
}