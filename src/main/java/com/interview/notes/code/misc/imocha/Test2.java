package com.interview.notes.code.misc.imocha;

import java.util.Date;

public class Test2 {
    protected int x, y;

    public static void main(String[] args) {

        Date date = new Date();

        System.err.println(date.getMonth() + " " + date.getDate());
        int k = 0;

        int ret = ++k + k++ + ++k + k;

        System.err.println(ret);
        Test2 t = new Test2();

        t.myMethod(null);

    }

    public void myMethod(String str) {

        System.err.println("string");

    }

    public void myMethod(Object obj) {

        System.err.println("object");

    }


}




