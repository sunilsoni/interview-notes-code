package com.interview.notes.code.year.y2025.july.amazon.test6;

import java.util.*;
/*

**Problem Statement:**

There is a company which has a CEO and a hierarchy of employees.
All employees have:

* A unique ID
* A name (not unique)
* A pointer to their manager
* A set of direct reports

You are required to implement the method `whoIsOurBoss()` that finds the **closest common manager** for two given employees.
That is, the **lowest common ancestor (LCA)** in the organizational tree — the manager **farthest from the CEO** that both employees report up to.

---

**Class Definition:**

```java
class Employee {
    long id;                    // unique ID
    String name;                // name (not unique)
    Employee manager;           // null for CEO
    Set<Employee> reports;      // empty set for a non-manager
}
```

---

 */
// Employee class definition
class Employee {
    long id;                  // unique ID
    String name;              // name (not unique)
    Employee manager;         // direct manager; null for CEO
    Set<Employee> reports;    // direct reports; empty for non-managers

    // Constructor to set id, name and manager; reports starts empty
    public Employee(long id, String name, Employee manager) {
        this.id = id;                             // assign unique ID
        this.name = name;                         // assign name
        this.manager = manager;                   // set manager pointer
        this.reports = new HashSet<>();           // initialize empty reports set
    }

    @Override
    public String toString() {
        return name + "(" + id + ")";             // for easy printing
    }
}

public class OrganizationUtils {

    /**
     * Finds the lowest common manager (LCA) of two employees.
     * Uses a set for fast lookups and Java 8 streams for clarity.
     *
     * @param emp1 First employee
     * @param emp2 Second employee
     * @return The closest common manager, or null if none
     */
    public static Employee whoIsOurBoss(Employee emp1, Employee emp2) {
        // 1. Collect all ancestors of emp1 (including emp1 itself)
        Set<Employee> ancestors1 = new HashSet<>();      // holds emp1's chain up to CEO
        Employee current = emp1;                         // start from emp1
        while (current != null) {
            ancestors1.add(current);                     // add this node to set
            current = current.manager;                   // move up one level
        }

        // 2. Build list of ancestors for emp2 in order: self, manager, manager's manager, ...
        List<Employee> ancestors2 = new ArrayList<>();   // preserves order from emp2 upward
        Employee cur2 = emp2;                            // start from emp2
        while (cur2 != null) {
            ancestors2.add(cur2);                        // append current ancestor
            cur2 = cur2.manager;                         // move up one level
        }

        // 3. Find the first ancestor of emp2 that is also in emp1's ancestor set
        return ancestors2.stream()
                .filter(ancestors1::contains)             // keep only common ancestors
                .findFirst()                              // take the closest one
                .orElse(null);                            // none found => null
    }

    /**
     * Helper to run a single test and print PASS/FAIL.
     */
    private static void runTest(String name, Employee e1, Employee e2, Employee expected) {
        Employee result = whoIsOurBoss(e1, e2);          // compute LCA
        boolean pass = Objects.equals(result, expected);
        System.out.printf("%-30s : %s (expected: %s, got: %s)%n",
                name, pass ? "PASS" : "FAIL",
                expected, result);
    }

    public static void main(String[] args) {
        // --- Build a small example hierarchy ---
        Employee ceo = new Employee(1, "CEO", null);
        Employee alice = new Employee(2, "Alice", ceo);
        Employee bob   = new Employee(3, "Bob",   ceo);
        ceo.reports.add(alice);
        ceo.reports.add(bob);

        Employee carol = new Employee(4, "Carol", alice);
        Employee dave  = new Employee(5, "Dave",  alice);
        alice.reports.add(carol);
        alice.reports.add(dave);

        Employee eve   = new Employee(6, "Eve",   bob);
        bob.reports.add(eve);

        // --- Basic Tests ---
        runTest("Same manager (Carol, Dave)", carol, dave, alice);
        runTest("Cross branches (Carol, Eve)", carol, eve, ceo);
        runTest("Self vs. self (Bob, Bob)",    bob,   bob,   bob);
        runTest("Manager and report (Alice, Dave)", alice, dave, alice);

        // --- Large Data Test: deep linear chain of 10_000 levels ---
        Employee prev = ceo;
        Employee deepA = null, deepB = null;
        for (int i = 1; i <= 10_000; i++) {
            Employee node = new Employee(100 + i, "N" + i, prev);
            prev.reports.add(node);
            prev = node;
            if (i == 5_000) deepA = node;        // pick one at mid-depth
            if (i == 10_000) deepB = node;       // pick the deepest
        }
        // The LCA of deepA and deepB should be deepA itself
        runTest("Large chain (depth 5k vs 10k)", deepA, deepB, deepA);
    }
}