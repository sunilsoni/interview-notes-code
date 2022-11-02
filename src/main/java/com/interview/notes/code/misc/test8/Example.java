package com.interview.notes.code.misc.test8;


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
    //  obj.display();
   }
}