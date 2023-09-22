package com.interview.notes.code.months.Sep23;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PizzaTreats1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfDays = scanner.nextInt();
        List<Day> days = new ArrayList<>();

        for (int i = 0; i < numberOfDays; i++) {
            int numberOfVisitors = scanner.nextInt();
            int numberOfPizzasOrdered = 0;

            days.add(new Day(numberOfVisitors, numberOfPizzasOrdered));
        }

        days.sort(Comparator.comparingInt(Day::getNumberOfVisitors).reversed());

        int numberOfUnusedCoupons = 0;
        for (Day day : days) {
            if (day.getNumberOfPizzasOrdered() < day.getNumberOfVisitors()) {
                day.setNumberOfPizzasOrdered(day.getNumberOfPizzasOrdered() + 1);
                numberOfUnusedCoupons--;
            }

            if (day.getNumberOfPizzasOrdered() == day.getNumberOfVisitors()) {
                day.setNumberOfPizzasOrdered(day.getNumberOfPizzasOrdered() + 2);
            }

            if (day.getNumberOfPizzasOrdered() > day.getNumberOfVisitors()) {
                System.out.println("NO");
                return;
            }
        }

        if (numberOfUnusedCoupons > 0) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
    }

    private static class Day {
        private int numberOfVisitors;
        private int numberOfPizzasOrdered;

        public Day(int numberOfVisitors, int numberOfPizzasOrdered) {
            this.numberOfVisitors = numberOfVisitors;
            this.numberOfPizzasOrdered = numberOfPizzasOrdered;
        }

        public int getNumberOfVisitors() {
            return numberOfVisitors;
        }

        public void setNumberOfVisitors(int numberOfVisitors) {
            this.numberOfVisitors = numberOfVisitors;
        }

        public int getNumberOfPizzasOrdered() {
            return numberOfPizzasOrdered;
        }

        public void setNumberOfPizzasOrdered(int numberOfPizzasOrdered) {
            this.numberOfPizzasOrdered = numberOfPizzasOrdered;
        }
    }
}
