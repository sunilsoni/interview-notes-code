package com.interview.notes.code.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List<VehicleLine> vehileLines = new ArrayList<>();
 * <p>
 * vehileLines.add(new VehicleLine("1234", "toyota","camry","lx","2020"));
 * <p>
 * vehileLines.add(new VehicleLine("abcd", "toyota","camry","lx","2020"));
 * <p>
 * vehileLines.add(new VehicleLine("456", "toyota","camry","lx","2020"));
 * <p>
 * vehileLines.add(new VehicleLine("sadas", "toyota","camry","lx","2020"));
 **/
class VehicleLine {

    private final String vin;
    private final String make;
    private final String model;
    private final String trim;
    private final String year;

    VehicleLine(String vin, String make, String model, String trim, String year) {

        this.vin = vin;

        this.make = make;

        this.model = model;

        this.trim = trim;

        this.year = year;

    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    @Override
    public String toString() {
        return make + " " + vin;
    }

}

public class Solution {

    public static void main(String[] args) {

        List<VehicleLine> vehileLines = new ArrayList<>();

        vehileLines.add(new VehicleLine("1234", "toyota", "camry", "lx", "2020"));

        vehileLines.add(new VehicleLine("abcd", "toyota", "camry", "lx", "2020"));

        vehileLines.add(new VehicleLine("456", "Honda", "camry", "lx", "2020"));

        vehileLines.add(new VehicleLine("sadas", "Hyundai", "camry", "lx", "2020"));

        System.out.println("vehileLines : " + vehileLines);
        List<VehicleLine> sortedVehileLines =
                vehileLines
                        .stream()
                        .sorted(Comparator.comparing(VehicleLine::getMake).thenComparing(VehicleLine::getVin))
                        .collect(Collectors.toList());

        System.out.println("sortedVehileLines : " + sortedVehileLines);


    }

}
