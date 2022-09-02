package com.interview.notes.code.misc.test6;

import java.util.Stack;

public class Test3 {
    public static void main(String[] args) {


        int i=5;
        i=i++ + ++i;
      //  System.out.println("%d",i);

//        int sum = 0;
//        for (int i = 0, j=0;  i < 5 & j < 5; ++i,j=i+1)
//            sum += i;
//        System.out.println(sum);

        Stack st = new Stack<>();
        st.push(1);
        st.push(1.1);
        st.push('z');
        st.push("hello");
        System.out.println(st);

    }
}
