package com.interview.notes.code.java8;

import lombok.Data;

import java.util.List;

@Data
public class Department {
    String name;
    List<Employee1> employee;
}
