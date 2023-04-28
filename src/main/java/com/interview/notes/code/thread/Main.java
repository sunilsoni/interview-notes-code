package com.interview.notes.code.thread;

import java.util.*;

final class Employee {
    private int employeeId;

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        return employeeId;
    }

 @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

   /*
    public boolean equals(Employee e) {
        return e.getEmployeeId()==this.employeeId;
    }*/

    private int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId){
        this.employeeId=employeeId;
    }
    @Override
    public String toString() {
        return String.valueOf(employeeId);
    }
}

public class Main {
    public static void main(String[] args) {
        Map<Employee, String> map = new HashMap<>();
        Employee e = new Employee(20);
        map.put(new Employee(10), "krishna");
        map.put(e, "Hari");
        e.setEmployeeId(100);
       map.put(new Employee(20), "Hari");
        System.out.println(map.get(e));
    }
}
