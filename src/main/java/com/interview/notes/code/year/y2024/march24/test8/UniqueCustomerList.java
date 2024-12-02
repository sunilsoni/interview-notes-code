package com.interview.notes.code.year.y2024.march24.test8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Customer {
    private int id;
    private String name;
    private String city;
    private int year;

    public Customer(int id, String name, String city, int year) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.year = year;
    }

    // Getters and Setters not shown for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (year != customer.year) return false;
        if (!name.equals(customer.name)) return false;
        return city.equals(customer.city);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", year=" + year +
                '}';
    }
}

public class UniqueCustomerList {

    public static List<Customer> getUniqueCustomers(String filePath) throws IOException {
        Set<Customer> customerSet = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String city = parts[2].trim();
                int year = Integer.parseInt(parts[3].trim());

                Customer customer = new Customer(id, name, city, year);
                customerSet.add(customer);
            }
        }
        reader.close();

        return new ArrayList<>(customerSet);
    }

    public static void main(String[] args) {
        String filePath = "/Users/ramki/Desktop/Java projects/HelloWorld/customer.txt";
        try {
            List<Customer> uniqueCustomers = getUniqueCustomers(filePath);
            uniqueCustomers.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
