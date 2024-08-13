package com.interview.notes.code.months.aug24.test16;

import java.util.HashSet;
import java.util.Set;

class Employee {
    long id;
    String name;
    Employee manager;
    Set<Employee> reports;

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
        this.manager = null;
        this.reports = new HashSet<>();
    }

    public static Employee whosOurBoss(Employee emp1, Employee emp2) {
        // Handle null cases
        if (emp1 == null && emp2 == null) {
            return null;
        }
        if (emp1 == null) {
            return emp2.manager != null ? emp2.manager : emp2;
        }
        if (emp2 == null) {
            return emp1.manager != null ? emp1.manager : emp1;
        }

        // Handle same employee case
        if (emp1 == emp2) {
            return emp1.manager != null ? emp1.manager : emp1;
        }

        // Handle CEO case
        if (emp1.manager == null) {
            return emp1;
        }
        if (emp2.manager == null) {
            return emp2;
        }

        Set<Employee> path1 = new HashSet<>();
        Employee current = emp1;
        while (current != null) {
            path1.add(current);
            current = current.manager;
        }

        current = emp2;
        while (current != null) {
            if (path1.contains(current)) {
                return current;
            }
            current = current.manager;
        }

        // This should never happen in a proper hierarchy
        return null;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
        if (manager != null) {
            manager.reports.add(this);
        }
    }
}
