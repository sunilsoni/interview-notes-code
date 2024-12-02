package com.interview.notes.code.year.y2024.jan24.test1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String json = "[{\"id\":1,\"name\":\"raj\",\"sal\":5000,\"dept_id\":1},{\"id\":222,\"name\":\"ravi\",\"sal\":2000,\"dept_id\":2},{\"id\":1111,\"name\":\"raji\",\"sal\":6000,\"dept_id\":1},{\"id\":322,\"name\":\"raghu\",\"sal\":3000,\"dept_id\":2}]";

        Map<Integer, String> departmentNames = new HashMap<>();
        departmentNames.put(1, "HR");
        departmentNames.put(2, "Finance"); // Add more departments as needed

        List<Employee> employees = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            int id = jsonObj.getInt("id");
            String name = jsonObj.getString("name");
            int salary = jsonObj.getInt("sal");
            int deptId = jsonObj.getInt("dept_id");
            employees.add(new Employee(id, name, salary, deptId));
        }

        Map<String, Integer> maxSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> departmentNames.getOrDefault(employee.getDeptId(), "Unknown"),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                                optionalEmployee -> optionalEmployee.map(Employee::getSalary).orElse(0))));

        maxSalaryByDepartment.forEach((departmentName, maxSalary) -> {
            System.out.println("Department Name: " + departmentName + ", Max Salary: " + maxSalary);
        });
    }
}

class Employee {
    private int id;
    private String name;
    private int salary;
    private int deptId;

    public Employee(int id, String name, int salary, int deptId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getDeptId() {
        return deptId;
    }
}
