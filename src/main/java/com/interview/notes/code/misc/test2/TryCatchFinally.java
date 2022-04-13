package com.interview.notes.code.misc.test2;

import java.util.*;

public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println(test());

        List list = new ArrayList();

        Set<String> set = new HashSet<>();

        ArrayList<Integer> al = new ArrayList<Integer>();

        // Custom input integer elements
        al.add(30);
        al.add(20);
        al.add(10);
        al.add(40);
        al.add(50);

        // Using sort() method of Collections class to
        // sort the elements and passing list and using
        // reverseOrder() method to sort in descending order
        Collections.sort(al, Collections.reverseOrder());
        HashMap map = new HashMap();

    }

    public static int test(){
        try{
            return  1;

        }catch(Exception e){
            return  2;

        }finally {
            return  3;
        }
    }
}
