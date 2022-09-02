package com.interview.notes.code.misc.test4;

public class Foo {

    int x = 10;

    public static void main(String[] args) {
        int a=5,b=7;
        int c=a+=2*3+b--;
        int count =0,i=0;
        do{

            count+=i;
            i++;
            if(count>5 )break;
        }while(i<=4);

        Foo foo = new Foo();
        System.out.println("Foo 1: " + c);

        foo.bar(990);
        System.out.println("Foo 2: " + foo.x);

    }

    void bar(int x) {

        x = x + 10;
    }
}

