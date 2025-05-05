package com.interview.notes.code.year.y2025.april.common.test13;

/**
 * Simulates the operation of a traffic light
 */
public class TrafficLightSimulator {
    private TrafficLight trafficLight;
    private boolean running;

    /**
     * Creates a new simulator with a default traffic light
     */
    public TrafficLightSimulator() {
        this.trafficLight = new TrafficLight();
        this.running = false;
    }

    /**
     * Creates a simulator with a specific traffic light
     */
    public TrafficLightSimulator(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
        this.running = false;
    }

    /**
     * Starts the simulation
     */
    public void start() {
        running = true;
        Thread simulationThread = new Thread(() -> {
            try {
                while (running) {
                    TrafficLightState currentState = trafficLight.getCurrentState();
                    int duration = trafficLight.getCurrentStateDuration();

                    System.out.println("Traffic light is now: " + currentState +
                            " for " + duration + " seconds");

                    // Sleep for the duration of the current state
                    Thread.sleep(duration * 1000);

                    // Transition to the next state
                    trafficLight.transition();
                }
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted: " + e.getMessage());
                running = false;
            }
        });

        simulationThread.start();
        System.out.println("Traffic light simulation started");
    }

    /**
     * Stops the simulation
     */
    public void stop() {
        running = false;
        System.out.println("Traffic light simulation stopped");
    }

    /**
     * Gets the traffic light being simulated
     */
    public TrafficLight getTrafficLight() {
        return trafficLight;
    }
}