package com.interview.notes.code.java8;




import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//list of employees from specific city(key) whose age is more than 40
public class EmployeeFilter {

    public static Map<String, List<Employee>> filterEmployees(List<Department> departments) {
        return departments.stream()
                .flatMap(department -> department.getEmployee().stream())
                .filter(employee -> employee.getAge() > 40)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

}