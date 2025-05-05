package com.interview.notes.code.year.y2025.april.common.test13;

/**
 * Main class that represents a traffic light
 */
public class TrafficLight {
    private TrafficLightState currentState;
    private int redDuration = 30;    // seconds
    private int yellowDuration = 5;  // seconds
    private int greenDuration = 25;  // seconds

    /**
     * Creates a new traffic light starting with the red light
     */
    public TrafficLight() {
        this.currentState = TrafficLightState.RED;
    }

    /**
     * Creates a traffic light with a specific initial state
     *
     * @param initialState the starting state of the traffic light
     */
    public TrafficLight(TrafficLightState initialState) {
        this.currentState = initialState;
    }

    /**
     * Gets the current state of the traffic light
     *
     * @return the current state
     */
    public TrafficLightState getCurrentState() {
        return currentState;
    }

    /**
     * Transitions to the next state based on the current state
     *
     * @return the new state after transition
     */
    public TrafficLightState transition() {
        switch (currentState) {
            case RED:
                currentState = TrafficLightState.GREEN;
                break;
            case GREEN:
                currentState = TrafficLightState.YELLOW;
                break;
            case YELLOW:
                currentState = TrafficLightState.RED;
                break;
        }
        return currentState;
    }

    /**
     * Sets custom durations for each light state
     */
    public void setDurations(int redDuration, int yellowDuration, int greenDuration) {
        this.redDuration = redDuration;
        this.yellowDuration = yellowDuration;
        this.greenDuration = greenDuration;
    }

    /**
     * Gets the duration of the current state in seconds
     *
     * @return duration in seconds
     */
    public int getCurrentStateDuration() {
        switch (currentState) {
            case RED:
                return redDuration;
            case YELLOW:
                return yellowDuration;
            case GREEN:
                return greenDuration;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the traffic light
     */
    @Override
    public String toString() {
        return "TrafficLight{state=" + currentState + ", duration=" + getCurrentStateDuration() + "s}";
    }
}