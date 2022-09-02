package com.interview.notes.code.misc.test4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MayTest {
    public static void main(String[] args) {

        callMe("S");
        String s = "abc".substring(1,2);

        //  Arrays.stream(new int[]{1,2,3}).map(i->System.out.println(" "+i));return i*2;}).sum();
        List<Integer> list = Arrays.asList(10, 23, -4, 0, 18);
        List<Integer> sortedList = list.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(sortedList);
    }
  /* public void foo(String s){

        try{
            callMe(s);
        }catch(Exception e){
            System.out.println("1");
        }catch(FileNotFoundException e2){
            System.out.println("2");
        }catch(IOException e3){
            System.out.println("3");
        }

    }*/

    public boolean foo(String s){



        callMe(s);

        return true;
    }

    private static  void callMe(String s) {

    }
    private static  void callMe1(String s) {
        String[] words = new String[13];
        String aWord = "It's cold here";
        words[0] = aWord;
        aWord= null;
        System.out.println(words[0]);


    }

}
