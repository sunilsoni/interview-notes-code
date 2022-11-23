package com.interview.notes.code.java.lambda;

import java.util.function.Function;

public class ComposedFunctionExample {

    /**
     * Find the Addrees of given employee from database and return pincode
     */
    public static void main(String[] args) {
        // Function<String, Address> first = empid -> EmployeeService.getEmployeesData().get(empid);
        Function<Address, Integer> second = addr -> addr.pincode;
        // extract("E101", first, second);

        int x = 8;
        System.out.println(x++);
    }

    static <T, R, U> U extract(T input, Function<T, R> first, Function<R, U> second) {
        return first.andThen(second).apply(input);
    }
}