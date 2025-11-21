package com.interview.notes.code.year.y2025.may.common.test16;

import java.util.*;

public class OrgHierarchy {
    // Finds the lowest common manager of two employees (using manager pointers)
    public static Employee findCommonManager(Employee a, Employee b) {
        if (a == null || b == null) return null;
        // use identity-based set to avoid recursive hashCode/equals on reports
        Set<Employee> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (Employee cur = a; cur != null; cur = cur.manager()) {
            seen.add(cur);
        }
        for (Employee cur = b; cur != null; cur = cur.manager()) {
            if (seen.contains(cur)) return cur;
        }
        return null;
    }

    public static void main(String[] args) {
        // Build hierarchy: CEO -> {M1, M2} -> {A, B} and {C, D}
        Employee ceo = new Employee(1, "CEO", null, new ArrayList<>());
        Employee m1 = new Employee(2, "M1", ceo, new ArrayList<>());
        Employee m2 = new Employee(3, "M2", ceo, new ArrayList<>());
        Employee a = new Employee(4, "A", m1, new ArrayList<>());
        Employee b = new Employee(5, "B", m1, new ArrayList<>());
        Employee c = new Employee(6, "C", m2, new ArrayList<>());
        Employee d = new Employee(7, "D", m2, new ArrayList<>());
        // populate reports
        ceo.reports().addAll(List.of(m1, m2));
        m1.reports().addAll(List.of(a, b));
        m2.reports().addAll(List.of(c, d));

        List<TestCase> tests = List.of(
                new TestCase(a, b, m1),
                new TestCase(a, c, ceo),
                new TestCase(c, d, m2),
                new TestCase(a, a, a),
                new TestCase(null, a, null)
        );

        for (TestCase t : tests) {
            Employee ans = findCommonManager(t.e1(), t.e2());
            String e1n = t.e1() == null ? "null" : t.e1().name();
            String e2n = t.e2() == null ? "null" : t.e2().name();
            String expn = t.expected() == null ? "null" : t.expected().name();
            String ansn = ans == null ? "null" : ans.name();
            String result = (ans == t.expected()) ? "PASS" : "FAIL";
            System.out.printf("Test(%s,%s): exp=%s, got=%s → %s%n", e1n, e2n, expn, ansn, result);
        }
    }

    // Employee record with id, name, manager pointer, and direct reports
    public record Employee(int id, String name, Employee manager, List<Employee> reports) {
    }

    // Test case holder
    public record TestCase(Employee e1, Employee e2, Employee expected) {
    }
}