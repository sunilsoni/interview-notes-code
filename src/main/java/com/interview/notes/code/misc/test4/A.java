package com.interview.notes.code.misc.test4;

public class A {

        int x = 5;


    public void fun(A b){
        b.x=10;
        System.out.println(b);
    }

    public static void main(String[] args) {
        A a = new A();
        a.fun(a);
        System.out.println("a.x: " + a.x);
        System.out.println(a);

         int x =0;
        try{
            x=5;
            throw new Exception();
           // x=10;
        } catch(Exception e) {
            x=15;
        } finally {
            x=20;
            System.out.println(x);
        }

    }


}

