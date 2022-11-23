package com.interview.notes.code.misc.test3;

class ABC {

    static int j = 1111;

    static {
        j = j-- - --j;    //L1 1111-1110=   1
    }

    {
        j = j++ + ++j;    //L2  0+1=1 
    }
}

class Y extends ABC {
    static {
        j = --j - j--;    //L3   1-0=0
    }

    {
        j = ++j + j++;    //L4   2+1=3
    }
}

public class DriverClass {
    public static void main(String[] args) {
        Y y = new Y();
        System.out.println(y.j);    //L5 3

    }
}
 
