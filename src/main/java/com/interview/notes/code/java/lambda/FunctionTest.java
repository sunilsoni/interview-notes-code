package com.interview.notes.code.java.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
//https://java-8-tips.readthedocs.io/en/stable/funcinterfaces.html
public class FunctionTest {

    public static void main(String[] args) {
        Function<Employee, String> empPrimaryId = (emp -> emp.getEmployeeId());
        Function<Department, String> deptPrimaryId = (dept -> dept.getLocationCode() + dept.getName());

        List<Employee> employeeList = new ArrayList();
        List<Department> deptList = new ArrayList();

        toMap(employeeList, empPrimaryId);
        toMap(deptList, deptPrimaryId);
    }

    static <T, R> Map<T, R> toMap(List<T> list, Function<T, R> func) {
        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, func.apply(t));
        }
        return result;
    }
}