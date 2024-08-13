package com.interview.notes.code.months.aug24.test17;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

/*
There is a company which has a CEO and a hierarchy of employees.
All employees have a unique ID, name and a pointer to their manager and their reports.
Please implement the wholsOurBoss() method to find the closest manager for two given employees
(i.e. the manager farthest from the CEO that both employees report up to).


class Employee (
long id; // unique ID
String name; // name (not unique)
Employee manager; // null for CEO
Set‹ Employee > reports; // empty set for a non-manager

 */
public class EmployeeHierarchySystem {

    private static final Logger logger = Logger.getLogger(EmployeeHierarchySystem.class.getName());

    public static void main(String[] args) {
        // Example usage
        Employee ceo = new Employee(1, "John CEO", EmployeeType.CEO);
        Employee vp1 = new Employee(2, "Alice VP", EmployeeType.VP);
        Employee vp2 = new Employee(3, "Bob VP", EmployeeType.VP);
        Employee manager1 = new Employee(4, "Charlie Manager", EmployeeType.MANAGER);
        Employee manager2 = new Employee(5, "David Manager", EmployeeType.MANAGER);
        Employee emp1 = new Employee(6, "Eve Employee", EmployeeType.EMPLOYEE);
        Employee emp2 = new Employee(7, "Frank Employee", EmployeeType.EMPLOYEE);

        vp1.setManager(ceo);
        vp2.setManager(ceo);
        manager1.setManager(vp1);
        manager2.setManager(vp2);
        emp1.setManager(manager1);
        emp2.setManager(manager2);

        Optional<Employee> commonManager = EmployeeHierarchyService.findLowestCommonManager(emp1, emp2);
        commonManager.ifPresent(manager ->
                System.out.println("Lowest common manager: " + manager)
        );
    }

    public enum EmployeeType {
        CEO, VP, MANAGER, EMPLOYEE
    }

    @Data
    @AllArgsConstructor
    public static class Employee {
        private final long id;
        private final String name;
        private final EmployeeType type;
        private Employee manager;
        private Set<Employee> reports = new HashSet<>();

        public Employee(long id, String name, EmployeeType type) {
            if (id < 0 || name == null || name.isEmpty() || type == null) {
                throw new IllegalArgumentException("Invalid employee data");
            }
            this.id = id;
            this.name = name;
            this.type = type;
        }

        public void setManager(Employee manager) {
            this.manager = manager;
            if (manager != null) {
                manager.reports.add(this);
            }
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', type=" + type + "}";
        }
    }

    public static class EmployeeHierarchyService {
        public static Optional<Employee> findLowestCommonManager(Employee emp1, Employee emp2) {
            logger.info("Finding lowest common manager for " + emp1 + " and " + emp2);

            // Handle null cases
            if (emp1 == null && emp2 == null) {
                return Optional.empty();
            }
            if (emp1 == null) {
                return Optional.ofNullable(emp2.getManager() != null ? emp2.getManager() : emp2);
            }
            if (emp2 == null) {
                return Optional.ofNullable(emp1.getManager() != null ? emp1.getManager() : emp1);
            }

            // Handle same employee case
            if (emp1 == emp2) {
                return Optional.ofNullable(emp1.getManager() != null ? emp1.getManager() : emp1);
            }

            // Handle CEO case
            if (emp1.getManager() == null) {
                return Optional.of(emp1);
            }
            if (emp2.getManager() == null) {
                return Optional.of(emp2);
            }

            Set<Employee> path1 = new HashSet<>();
            Employee current = emp1;
            while (current != null) {
                path1.add(current);
                current = current.getManager();
            }

            current = emp2;
            while (current != null) {
                if (path1.contains(current)) {
                    return Optional.of(current);
                }
                current = current.getManager();
            }

            // This should never happen in a proper hierarchy
            logger.warning("No common manager found for " + emp1 + " and " + emp2);
            return Optional.empty();
        }
    }
}
