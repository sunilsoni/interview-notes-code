package com.interview.notes.code.year.y2025.jan25.amazon.test14;

// Base interface for all Alexa devices
interface AlexaDevice {
    void reportStatus();
}

// Interface for devices with power source
interface PowerSource {
    String getPowerStatus();
}

// Output interfaces
interface Speaker {
    void speak(String message);
}

interface Display {
    void show(String message);
}

// Classes for different power sources
class BatteryPower implements PowerSource {
    private int batteryLevel;
    private boolean isCharging;

    public BatteryPower(int level, boolean charging) {
        this.batteryLevel = level;
        this.isCharging = charging;
    }

    public String getPowerStatus() {
        return "Current battery level is " + batteryLevel + "%" +
                (isCharging ? " and charging" : "");
    }
}

class PluggedPower implements PowerSource {
    public String getPowerStatus() {
        return "Device is plugged into power";
    }
}

// Concrete device implementations
class EchoDot implements AlexaDevice, Speaker {
    private PowerSource power;

    public EchoDot() {
        this.power = new PluggedPower();
    }

    public void speak(String message) {
        System.out.println("Speaking: " + message);
    }

    public void reportStatus() {
        speak(power.getPowerStatus());
    }
}

class Tablet implements AlexaDevice, Speaker, Display {
    private PowerSource power;

    public Tablet(int batteryLevel, boolean charging) {
        this.power = new BatteryPower(batteryLevel, charging);
    }

    public void speak(String message) {
        System.out.println("Speaking: " + message);
    }

    public void show(String message) {
        System.out.println("Displaying: " + message);
    }

    public void reportStatus() {
        String status = power.getPowerStatus();
        speak(status);
        show(status);
    }
}

// Test class
public class AlexaDeviceTest {
    public static void main(String[] args) {
        // Test Case 1: Tablet with battery
        System.out.println("Test Case 1: Tablet with 75% battery, charging");
        Tablet tablet = new Tablet(75, true);
        tablet.reportStatus();

        // Test Case 2: Echo Dot (plugged device)
        System.out.println("\nTest Case 2: Echo Dot (always plugged)");
        EchoDot echoDot = new EchoDot();
        echoDot.reportStatus();

        // Add more test cases here
        testEdgeCases();
    }

    private static void testEdgeCases() {
        System.out.println("\nTesting Edge Cases:");

        // Test battery at 0%
        System.out.println("Test Case 3: Tablet with 0% battery");
        Tablet tabletEmpty = new Tablet(0, true);
        tabletEmpty.reportStatus();

        // Test battery at 100%
        System.out.println("\nTest Case 4: Tablet with 100% battery");
        Tablet tabletFull = new Tablet(100, false);
        tabletFull.reportStatus();
    }
}
