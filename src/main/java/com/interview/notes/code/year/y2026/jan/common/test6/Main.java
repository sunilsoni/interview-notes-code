package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.List;

record Employee(String name,int age,String gender){}

public class Main{
    static List<Employee> above50(List<Employee> e){
        return e.stream().filter(x->x.age()>50).toList();
    }
    public static void main(String[] args){
        var emps=List.of(
                new Employee("Asha",55,"F"),
                new Employee("Ravi",49,"M"),
                new Employee("Meera",60,"F"),
                new Employee("Vikram",50,"M"),
                new Employee("Neha",35,"F")
        );

        System.out.println(above50(emps));
    }
}
