package com.interview.notes.code.months.sept24.wallmart.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BadgeAccessSystem {
    private Map<String, Employee> employees;

    public BadgeAccessSystem() {
        this.employees = new HashMap<>();
    }

    public void processRecords(List<BadgeRecord> records) {
        for (BadgeRecord record : records) {
            String name = record.getEmployeeName();
            String action = record.getAction();

            Employee employee = employees.computeIfAbsent(name, Employee::new);

            if ("enter".equalsIgnoreCase(action)) {
                employee.enter();
            } else if ("exit".equalsIgnoreCase(action)) {
                employee.exit();
            }
        }
    }

    public List<String> getEmployeesWhoDidNotBadgeOut() {
        List<String> result = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.didNotBadgeOut()) {
                result.add(employee.getName());
            }
        }
        return result;
    }

    public List<String> getEmployeesWhoDidNotBadgeIn() {
        List<String> result = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.didNotBadgeIn()) {
                result.add(employee.getName());
            }
        }
        return result;
    }
}