package com.interview.notes.code.year.y2025.september.common.test3;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class EmployeeDTO {
    String id;
    String name;
    String email;

    public EmployeeDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

class Employee {
    String id;
    String name;
    List<String> emails;

    public Employee(String id, String name, List<String> emails) {
        this.id = id;
        this.name = name;
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emails=" + emails +
                '}';
    }
}

public class EmployeeConverter {

    public static void main(String[] args) {
        // Sample input list
        List<EmployeeDTO> employeeDTOS = Arrays.asList(
                new EmployeeDTO("1", "sachin", null),
                new EmployeeDTO("2", "rahul", "rahul@gmail"),
                new EmployeeDTO("2", "rahul", "rahul@hotmail")
        );

        // Grouping by composite key (id + name), then collecting emails
        List<Employee> employees = employeeDTOS.stream()
                .collect(Collectors.groupingBy(
                        dto -> dto.id + "|" + dto.name, // composite key
                        LinkedHashMap::new,             // maintain order
                        Collectors.mapping(dto -> dto, Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> {
                    List<EmployeeDTO> dtos = entry.getValue();
                    EmployeeDTO first = dtos.get(0);
                    List<String> emailList = dtos.stream()
                            .map(dto -> dto.email)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                    return new Employee(first.id, first.name, emailList.isEmpty() ? null : emailList);
                })
                .collect(Collectors.toList());

        // Print output for verification
        employees.forEach(System.out::println);
    }
}