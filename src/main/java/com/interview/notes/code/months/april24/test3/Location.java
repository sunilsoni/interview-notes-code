package com.interview.notes.code.months.april24.test3;

public enum Location {
    BOSTON_MA("Boston, MA"),
    NEW_YORK_NY("New York, NY"),
    LOS_ANGELES_CA("Los Angeles, CA"),
    CHICAGO_IL("Chicago, IL");

    private final String displayName;

    Location(String displayName) {
        this.displayName = displayName;
    }

    // You might also include a method to get a Location enum from a string.
    public static Location fromString(String text) {
        for (Location loc : Location.values()) {
            if (loc.displayName.equalsIgnoreCase(text)) {
                return loc;
            }
        }
        throw new IllegalArgumentException("Unknown location: " + text);
    }

    public String getDisplayName() {
        return displayName;
    }
}
