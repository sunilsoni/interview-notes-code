package com.interview.notes.code.year.y2025.jan24.test17;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            throw new NumberFormatException();
        } catch (NumberFormatException e) {
            // Handle NumberFormatException specifically
        } catch (Exception e) {
            // Handle any other exceptions
        } finally {
            System.out.println("done");
        }

    }

    public void remove() {
        List<Employee> lst = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setName("Abinash");
        Employee e2 = new Employee();
        e2.setName("Dummy");
        Employee e3 = new Employee();
        e3.setName("Chris");
        Employee e4 = new Employee();
        e4.setName("Test");

        lst.add(e1);
        lst.add(e2);
        lst.add(e3);
        lst.add(e4);

        // Using removeIf to remove an element based on a condition
        lst.removeIf(e -> "Abinash".equalsIgnoreCase(e.getName()));
    }
}
