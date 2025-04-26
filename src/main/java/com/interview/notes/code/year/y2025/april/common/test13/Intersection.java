package com.interview.notes.code.year.y2025.april.common.test13;

/**
 * Represents a traffic intersection with multiple traffic lights
 */
public class Intersection {
    private TrafficLight northSouth;
    private TrafficLight eastWest;
    
    /**
     * Creates a new intersection with default traffic lights
     */
    public Intersection() {
        // North-South starts with green
        this.northSouth = new TrafficLight(TrafficLightState.GREEN);
        
        // East-West starts with red
        this.eastWest = new TrafficLight(TrafficLightState.RED);
    }
    
    /**
     * Transitions the intersection to the next state
     */
    public void transition() {
        TrafficLightState nsState = northSouth.getCurrentState();
        TrafficLightState ewState = eastWest.getCurrentState();
        
        // Ensure opposite directions have complementary states
        if (nsState == TrafficLightState.RED) {
            if (ewState == TrafficLightState.GREEN) {
                eastWest.transition(); // GREEN -> YELLOW
            } else if (ewState == TrafficLightState.YELLOW) {
                eastWest.transition(); // YELLOW -> RED
                northSouth.transition(); // RED -> GREEN
            }
        } else if (nsState == TrafficLightState.YELLOW) {
            northSouth.transition(); // YELLOW -> RED
            eastWest.transition(); // RED -> GREEN
        } else if (nsState == TrafficLightState.GREEN) {
            northSouth.transition(); // GREEN -> YELLOW
        }
    }
    
    /**
     * Gets the current state of the intersection
     */
    public String getState() {
        return "North-South: " + northSouth.getCurrentState() + 
               ", East-West: " + eastWest.getCurrentState();
    }
    
    /**
     * Gets the North-South traffic light
     */
    public TrafficLight getNorthSouthLight() {
        return northSouth;
    }
    
    /**
     * Gets the East-West traffic light
     */
    public TrafficLight getEastWestLight() {
        return eastWest;
    }
}