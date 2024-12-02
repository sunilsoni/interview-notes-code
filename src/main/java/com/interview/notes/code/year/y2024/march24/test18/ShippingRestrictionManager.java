package com.interview.notes.code.year.y2024.march24.test18;

import java.util.ArrayList;
import java.util.List;

public class ShippingRestrictionManager {
    private final List<GeographicalArea> restrictedAreas;

    public ShippingRestrictionManager() {
        this.restrictedAreas = new ArrayList<>();
    }

    public void addGeographicalRestriction(GeographicalArea area) {
        this.restrictedAreas.add(area);
    }

    // Checks if any of the areas include the address.
    public boolean isShippingRestricted(Address address) {
        return restrictedAreas.stream().anyMatch(area -> area.containsAddress(address));
    }
}