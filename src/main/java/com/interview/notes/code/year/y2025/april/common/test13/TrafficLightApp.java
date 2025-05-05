package com.interview.notes.code.year.y2025.april.common.test13;

/**
 * Main application to demonstrate the traffic light system
 */
public class TrafficLightApp {
    public static void main(String[] args) {
        // Create a traffic light with custom durations
        TrafficLight trafficLight = new TrafficLight(TrafficLightState.RED);
        trafficLight.setDurations(20, 5, 15); // Red: 20s, Yellow: 5s, Green: 15s

        // Create and start the simulator
        TrafficLightSimulator simulator = new TrafficLightSimulator(trafficLight);
        simulator.start();

        // Let the simulation run for 2 minutes
        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the simulation
        simulator.stop();
    }
}