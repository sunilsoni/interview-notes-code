package com.interview.notes.code.year.y2024.aug24.test32;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryHandler {

    public List<String> handleInventory(List<Car> carsList) {
        return carsList.stream()
                .filter(car -> car.getMiles() <= 50000)
                .map(car -> String.format("%s (%d) - %d miles", car.getName(), car.getYear(), car.getMiles()))
                .collect(Collectors.toList());
    }
}