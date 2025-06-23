package com.interview.notes.code.year.y2025.may.common.test17;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OrgHierarchy {
    // Finds the lowest common manager of two employees
    public static Employee findCommonManager(Employee a, Employee b) {
        if (a == null || b == null) return null;
        Set<Employee> seen = new HashSet<>();
        for (Employee cur = a; cur != null; cur = cur.manager()) seen.add(cur);
        for (Employee cur = b; cur != null; cur = cur.manager())
            if (seen.contains(cur)) return cur;
        return null;
    }

    public static void main(String[] args) {
        Employee ceo = new Employee("CEO", null);
        Employee m1 = new Employee("M1", ceo);
        Employee m2 = new Employee("M2", ceo);
        Employee a = new Employee("A", m1);
        Employee b = new Employee("B", m1);
        Employee c = new Employee("C", m2);
        Employee d = new Employee("D", m2);

        record Test(Employee e1, Employee e2, Employee exp) {
        }
        List<Test> tests = List.of(
                new Test(a, b, m1),
                new Test(a, c, ceo),
                new Test(c, d, m2),
                new Test(a, a, a),
                new Test(null, a, null)
        );

        tests.forEach(t -> {
            Employee ans = findCommonManager(t.e1(), t.e2());
            String e1n = (t.e1() == null ? "null" : t.e1().name());
            String e2n = (t.e2() == null ? "null" : t.e2().name());
            String expn = (t.exp() == null ? "null" : t.exp().name());
            String ansn = (ans == null ? "null" : ans.name());
            System.out.printf("Test(%s,%s): exp=%s, got=%s → %s%n",
                    e1n, e2n, expn, ansn,
                    Objects.equals(ans, t.exp()) ? "PASS" : "FAIL");
        });
    }

    // Immutable employee record with reference to its manager
    public record Employee(String name, Employee manager) {
    }
}