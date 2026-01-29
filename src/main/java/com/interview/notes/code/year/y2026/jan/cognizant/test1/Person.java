package com.interview.notes.code.year.y2026.jan.cognizant.test1;

// Define a record
public record Person(String name, int age) {
}

class RecordDemo {
    public static void main(String[] args) {
        // Create instances of the record
        Person p1 = new Person("Sunil", 35);
        Person p2 = new Person("Anita", 28);

        // Access fields using accessor methods
        System.out.println(p1.name()); // Output: Sunil
        System.out.println(p1.age());  // Output: 35

        // Records automatically implement toString()
        System.out.println(p1); // Output: Person[name=Sunil, age=35]

        // Records automatically implement equals() and hashCode()
        Person p3 = new Person("Sunil", 35);
        System.out.println(p1.equals(p3)); // Output: true
    }
}
