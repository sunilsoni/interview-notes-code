package com.interview.notes.code.year.y2026.march.common.test2;

// Parent Class
class Operation {
    // The base method
    public int calculate(int a, int b) {
        System.out.println("Performing a generic operation...");
        return 0; 
    }
}

// Subclass 1: Addition
class Addition extends Operation {
    // Overriding the parent's calculate method
    @Override
    public int calculate(int a, int b) {
        System.out.print("Performing addition: ");
        return a + b;
    }
}

// Subclass 2: Subtraction
class Subtraction extends Operation {
    // Overriding the parent's calculate method again
    @Override
    public int calculate(int a, int b) {
        System.out.print("Performing subtraction: ");
        return a - b;
    }
}

// The main program
public class CalculatorRunner {
    public static void main(String[] args) {
        
        // POLYMORPHISM IN ACTION:
        // The reference type is the parent (Operation), 
        // but the actual object created is the child.
        Operation op1 = new Addition();
        Operation op2 = new Subtraction();

        // The program doesn't know which 'calculate' to use until the exact moment the code runs.
        System.out.println(op1.calculate(10, 5));  // Output: Performing addition: 15
        System.out.println(op2.calculate(10, 5));  // Output: Performing subtraction: 5
    }
}