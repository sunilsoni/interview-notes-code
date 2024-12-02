package com.interview.notes.code.year.y2024.june24.test8;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMergeExample {
    public static void main(String[] args) {
        // Sample data for personal details
        List<PersonalDetails> personalDetailsList = new ArrayList<>();
        // Populate personalDetailsList with some data

        // Sample data for employee details
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        // Populate employeeDetailsList with some data

        // Combine the information from both lists into a single list using Java 8 streams
       /* List<Employee> combinedList = Stream.concat(
                personalDetailsList.stream().map(pd -> new Employee(pd.getId(), pd.getName(), "", 0.0)), // Assuming empty values for designation and salary
                employeeDetailsList.stream().map(ed -> new Employee(ed.getEmployeeId(), "", ed.getDesignation(), ed.getSalary()))
        ).collect(Collectors.toList());*/

        // Print the combined list
        //combinedList.forEach(System.out::println);
    }

    static class Employee {
        private int id;
        private String name;
        private String designation;
        private double salary;

        public Employee(int id, String name, String designation, double salary) {
            this.id = id;
            this.name = name;
            this.designation = designation;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", designation='" + designation + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
