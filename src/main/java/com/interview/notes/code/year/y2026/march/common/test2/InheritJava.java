package com.interview.notes.code.year.y2026.march.common.test2;

/**
 * Main Class: InheritJava
 * Demonstrates: Interface vs Abstract Class, Method Overriding, and Polymorphism.
 */
public class InheritJava {

    // 5. TEST EXECUTION
    public static void main(String[] args) {
        System.out.println("--- Starting Transport Test --- \n");

        // Reference: Vehicle | Object: Vehicle
        Vehicle vehicle = new Vehicle();

        // Reference: Abstract Class | Object: Vehicle
        TransportMode mode = new Vehicle();

        // Reference: Interface | Object: Vehicle
        ITransport transport = new Vehicle();

        // Reference: Interface | Object: SmallVehicle
        ITransport smallTransport = new SmallVehicle();

        // TESTING MOVE (Polymorphism in action)
        // Even though references differ, Java calls the method of the actual Object on the heap.
        vehicle.move();        // Output: Vehicle: Moving on wheels
        mode.move();           // Output: Vehicle: Moving on wheels
        transport.move();      // Output: Vehicle: Moving on wheels
        smallTransport.move(); // Output: SmallVehicle: Moving quickly on wheels

        System.out.println("\n--- Testing Start Logic ---");

        // TESTING START (Inheritance in action)
        // Since no subclass overrode start(), they all climb the ladder to TransportMode.
        vehicle.start();
        mode.start();
        transport.start();
        smallTransport.start();
    }

    // 1. THE CONTRACT: Defines what a transport MUST do.
    public interface ITransport {
        void move();
        void start();
    }

    // 2. THE TEMPLATE: Provides a "General Way" (Default Implementation).
    // Marking it 'static' allows us to use it inside the main method of InheritJava.
    public static abstract class TransportMode implements ITransport {
        @Override
        public void move() {
            System.out.println("TransportMode: Moving in general way");
        }

        @Override
        public void start() {
            System.out.println("TransportMode: Starting transport");
        }
    }

    // 3. THE SPECIALIZATION: Overrides 'move' but inherits 'start'.
    public static class Vehicle extends TransportMode {
        @Override
        public void move() {
            System.out.println("Vehicle: Moving on wheels");
        }
        // Note: It does NOT override start(), so it uses TransportMode's start().
    }

    // 4. THE SUB-SPECIALIZATION: Further refines 'move'.
    public static class SmallVehicle extends Vehicle {
        @Override
        public void move() {
            System.out.println("SmallVehicle: Moving quickly on wheels");
        }
    }
}