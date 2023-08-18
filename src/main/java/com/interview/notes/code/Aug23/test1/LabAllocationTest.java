package com.interview.notes.code.Aug23.test1;

import java.util.*;
public class LabAllocationTest {
    
    public static String labAllocation(String input) {
        String[] values = input.split(",");
        int c1 = Integer.parseInt(values[0]);
        int c2 = Integer.parseInt(values[1]);
        int c3 = Integer.parseInt(values[2]);
        int trainees = Integer.parseInt(values[3]);
        
        List<String> labs = new ArrayList<>();
        if(c1 >= trainees) {
            labs.add("L1");
        }
        if(c2 >= trainees) {
            labs.add("L2");
        }
        if(c3 >= trainees) {
            labs.add("L3");
        }
        
        if(labs.isEmpty()) {
            return "No Labs";
        } else {
            return "Lab - " + String.join(",", labs);
        }
    }
    
    public static void main(String args[]) {
      String str1 = "30,40,20,25";
      System.out.println(labAllocation(str1));
      String str2 = "30,25,30,25";
      System.out.println(labAllocation(str2));
      String str3 = "40,30,40,55";
      System.out.println(labAllocation(str3));
      
    }
}
