package com.interview.notes.code.year.y2026.august.Bsnf.test3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

record Train(String name, int remaining, int unlock){}

public class TrainDispatcher {
    public static void main(String[] args) {

        String[] trains = {"Red", "Red", "Red", "Blue", "Blue", "Blue"};

        int n = 2;
        List<String>  result = minimumDispatchTime(trains,n);
        System.out.println("Output"+result);
       // Output: 7
    }


    public static List<String>  minimumDispatchTime(String[] trains,int n){

        var counts = Arrays.stream(trains)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println("counts "+counts);
        
        var available = new PriorityQueue<Train>((a,b)->b.remaining()-a.remaining());

        counts.forEach((name, count)-> available.add(new Train(name,count.intValue(),0)));
        System.out.println("availablepq "+available); 
        var schedule=new ArrayList<String>();

        var cooldown=new LinkedList<Train>();
        var time=0;
        while(!available.isEmpty() ){
                if(!cooldown.isEmpty() && cooldown.peek().unlock()<=time){
                    available.add(cooldown.poll());
                }


                if(!available.isEmpty() ){
                    var curr=available.poll();
                    schedule.add(curr.name())
 ;               }else{
      schedule.add("idle");
 }
 time++;
        }

 

     return  schedule;

    }
}
