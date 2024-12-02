package com.interview.notes.code.year.y2024.aug24.test16;

public class Main {
    public static void main(String[] args) {
        // Create the company hierarchy
        Employee ceo = new Employee(1, "CEO");
        Employee vp1 = new Employee(2, "VP1");
        Employee vp2 = new Employee(3, "VP2");
        Employee manager1 = new Employee(4, "Manager1");
        Employee manager2 = new Employee(5, "Manager2");
        Employee employee1 = new Employee(6, "Employee1");
        Employee employee2 = new Employee(7, "Employee2");

        vp1.setManager(ceo);
        vp2.setManager(ceo);
        manager1.setManager(vp1);
        manager2.setManager(vp2);
        employee1.setManager(manager1);
        employee2.setManager(manager2);

        // Test cases
        System.out.println("Case 1: " + Employee.whosOurBoss(employee1, employee2).name);  // Should be CEO
        System.out.println("Case 2: " + Employee.whosOurBoss(employee1, manager1).name);  // Should be Manager1
        System.out.println("Case 3: " + Employee.whosOurBoss(vp1, vp2).name);  // Should be CEO
        System.out.println("Case 4: " + Employee.whosOurBoss(ceo, employee1).name);  // Should be CEO
        System.out.println("Case 5: " + Employee.whosOurBoss(employee1, employee1).name);  // Should be Manager1
        System.out.println("Case 6: " + Employee.whosOurBoss(ceo, ceo).name);  // Should be CEO
        System.out.println("Case 7: " + (Employee.whosOurBoss(employee1, null) == manager1 ? "Correct" : "Incorrect"));  // Should be Manager1
        System.out.println("Case 8: " + (Employee.whosOurBoss(null, null) == null ? "Correct" : "Incorrect"));  // Should be null
    }
}