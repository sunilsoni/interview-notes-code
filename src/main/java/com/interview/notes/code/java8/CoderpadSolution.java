package com.interview.notes.code.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Given car sales per country and model,
 * find the top three countries by sales  per model
 * <p>
 * Below is the sample expected output for corolla.
 * <p>
 * "Mexico", "corolla",600
 * "USA", "corolla",250
 * "India", "corolla",200
 * <p>
 * similarly  for all other modles.
 */


public class CoderpadSolution {

    private static <T> Collector<T, ?, List<T>> limit(int limit) {
        return Collector.of(ArrayList::new,
                (l, e) -> {
                    if (l.size() < limit) l.add(e);
                },
                (l1, l2) -> {
                    l1.addAll(l2.subList(0, Math.min(l2.size(), Math.max(0, limit - l1.size()))));
                    return l1;
                });
    }

    public static void main(String[] args) {
        List<CarSales> carSales = new ArrayList<>();
        carSales.add(new CarSales("India", "camry", 50));
        carSales.add(new CarSales("India", "corolla", 200));
        carSales.add(new CarSales("India", "yaris", 300));
        carSales.add(new CarSales("USA", "camry", 400));
        carSales.add(new CarSales("USA", "corolla", 250));
        carSales.add(new CarSales("USA", "yaris", 100));
        carSales.add(new CarSales("USA", "specialedition", 10));
        carSales.add(new CarSales("Mexico", "camry", 90));
        carSales.add(new CarSales("Mexico", "corolla", 600));
        carSales.add(new CarSales("Mexico", "yaris", 102));
        carSales.add(new CarSales("Brazil", "camry", 90));
        carSales.add(new CarSales("Brazil", "corolla", 50));
        carSales.add(new CarSales("Brazil", "yaris", 102));

        new CoderpadSolution().findHighestSales(carSales);
    }

    public void findHighestSales(List<CarSales> carSales) {

        Map<String, List<CarSales>> top3CarSalesByCountry = carSales.stream()
                .sorted(Comparator.comparingLong(CarSales::getVolume).reversed())
                .collect(Collectors.groupingBy(CarSales::getModel, limit(3)));

        top3CarSalesByCountry.forEach((k, v) -> {
            System.out.println("Model: " + k);

            for (CarSales s : v) {
                System.out.println("Country:  " + s.getCountry() + " Model: " + s.getModel() + " Volume: " + s.getVolume());
            }
            System.out.println();
            System.out.println();
            System.out.println();

        });


    }

    public static class CarSales {

        public String country;
        public String model;
        public long volume;

        public CarSales(String country, String model, long volume) {
            this.country = country;
            this.model = model;
            this.volume = volume;
        }

        public String getCountry() {
            return country;
        }

        public String getModel() {
            return model;
        }

        public long getVolume() {
            return volume;
        }
    }

}
