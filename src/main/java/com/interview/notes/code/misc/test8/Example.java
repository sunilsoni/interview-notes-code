package com.interview.notes.code.misc.test8;


import java.util.ArrayList;
import java.util.List;

class ParentTest {
   private void display() {
      System.out.println("Super class");    
   }
}

public class Example extends Parent {
   void display() {// trying to override display() {
      System.out.println("Sub class");
   }
   public static void main(String[] args) {
      Parent obj = new Example();

      List<String>  list = new ArrayList<>();
      list.add("A");
      list.add("B");
      int index=0;
      for(String s : list){
         if("B"==s){
            list.remove(index);
         }
         index++;
      }

    //  obj.display();
   }
}