package com.interview.notes.code.java8;

import lombok.Data;
import org.apache.commons.lang3.function.Failable;

import java.util.List;

@Data
public class Department {
    String name;
    List<Employee> employee;
}
