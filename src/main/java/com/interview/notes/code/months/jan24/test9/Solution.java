package com.interview.notes.code.months.jan24.test9;

import java.util.Scanner;

// Define the Animal interface
interface Animal {
    void eat();

    void makeSound();
}

// Define the Bird interface with default legs value
interface Bird {
    int legs = 2;

    void fly();
}

// Define the Parrot class that implements Animal and Bird interfaces
class Parrot implements Animal, Bird {
    public void eat() {
        System.out.println("Parrots can eat up to 100 gms in a day");
    }

    public void makeSound() {
        System.out.println("Parrots make sound of screech");
    }

    public void fly() {
        System.out.println("Parrots can fly up to 50 miles in a day");
    }
}

// Main class to run the program
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            String name = sc.nextLine();
            Parrot p = new Parrot();
            if (name.equals("eat")) {
                p.eat();
            } else if (name.equals("makeSound")) {
                p.makeSound();
            } else if (name.equals("fly")) {
                p.fly();
            }
        }
        sc.close();
    }
}
