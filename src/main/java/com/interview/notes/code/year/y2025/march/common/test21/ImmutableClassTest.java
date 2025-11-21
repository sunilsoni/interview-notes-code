package com.interview.notes.code.year.y2025.march.common.test21;

import java.util.Date;

public class ImmutableClassTest {
    public static void main(String[] args) {
        // Create an instance of ImmutableClass
        Date birthDate = new Date();
        ImmutableClass immutable = new ImmutableClass("Sunil", 25, birthDate);

        // Test values set via constructor
        System.out.println("Name: " + immutable.name()); // Expected: Sunil
        System.out.println("Age: " + immutable.age());   // Expected: 25
        System.out.println("BirthDate: " + immutable.birthDate()); // Expected: current date

        // Test immutability
        System.out.println("\nTesting immutability...");
        birthDate.setTime(0); // Modify original Date object
        System.out.println("Original Date Modified: " + birthDate);
        System.out.println("ImmutableClass Date: " + immutable.birthDate()); // Should NOT reflect the modification

        Date returnedDate = immutable.birthDate();
        returnedDate.setTime(0); // Modify the Date from getter
        System.out.println("Modified Returned Date: " + returnedDate);
        System.out.println("ImmutableClass Date: " + immutable.birthDate()); // Should still remain unchanged

        // Assertions (simulated)
        if (!immutable.name().equals("Sunil")) {
            System.out.println("Test failed: Name mismatch");
        } else {
            System.out.println("Test passed: Name is correct");
        }

        if (immutable.birthDate().equals(birthDate)) {
            System.out.println("Test failed: Mutability detected");
        } else {
            System.out.println("Test passed: ImmutableClass is immutable");
        }
    }
}
