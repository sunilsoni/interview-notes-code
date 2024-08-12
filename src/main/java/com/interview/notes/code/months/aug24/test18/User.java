package com.interview.notes.code.months.aug24.test18;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class User {
    int id;
    int internalId;
    String firstName;
    String lastName;
    int age;
    LocalDate dateOfBirth;
    String email;
    String gender;
    String country;
    String city;
    String address;
    String zipCode;
    String phoneNumber;
    String department;
    String role;
    LocalDate joiningDate;
    int salary;
    String status;

    // Constructor
    public User(String[] data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        this.id = Integer.parseInt(data[0]);
        this.internalId = Integer.parseInt(data[1]);
        this.firstName = data[2];
        this.lastName = data[3];
        this.age = Integer.parseInt(data[4]);
        this.dateOfBirth = LocalDate.parse(data[5], formatter);
        this.email = data[6];
        this.gender = data[7];
        this.country = data[8];
        this.city = data[9];
        this.address = data[10];
        this.zipCode = data[11];
        this.phoneNumber = data[12];
        this.department = data[13];
        this.role = data[14];
        this.joiningDate = LocalDate.parse(data[15], formatter);
        this.salary = Integer.parseInt(data[16]);
        this.status = data[17];
    }

    // Getters and setters (omitted for brevity)
}

class UserManager {
    public static List<User>[] compareUsers(List<User> usersListInDB, List<User> newUsersList) {
        List<User> updatedUsers = new ArrayList<>();
        List<User> insertedUsers = new ArrayList<>();

        Map<Integer, User> existingUsers = new HashMap<>();
        for (User user : usersListInDB) {
            existingUsers.put(user.id, user);
        }

        for (User newUser : newUsersList) {
            if (newUser.id == 0) {
                insertedUsers.add(newUser);
            } else {
                User existingUser = existingUsers.get(newUser.id);
                if (existingUser != null) {
                    if (!compareUserAttributes(existingUser, newUser)) {
                        updatedUsers.add(newUser);
                    }
                }
            }
        }

        return new List[]{updatedUsers, insertedUsers};
    }

    private static boolean compareUserAttributes(User existingUser, User newUser) {
        return existingUser.internalId == newUser.internalId &&
               existingUser.firstName.equals(newUser.firstName) &&
               existingUser.lastName.equals(newUser.lastName) &&
               existingUser.age == newUser.age &&
               existingUser.dateOfBirth.equals(newUser.dateOfBirth) &&
               existingUser.email.equals(newUser.email) &&
               existingUser.gender.equals(newUser.gender) &&
               existingUser.country.equals(newUser.country) &&
               existingUser.city.equals(newUser.city) &&
               existingUser.address.equals(newUser.address) &&
               existingUser.zipCode.equals(newUser.zipCode) &&
               existingUser.phoneNumber.equals(newUser.phoneNumber) &&
               existingUser.department.equals(newUser.department) &&
               existingUser.role.equals(newUser.role) &&
               existingUser.joiningDate.equals(newUser.joiningDate) &&
               existingUser.salary == newUser.salary &&
               existingUser.status.equals(newUser.status);
    }

    public static void main(String[] args) {
        // Sample Case 0
        List<User> usersListInDB = new ArrayList<>();
        usersListInDB.add(new User(new String[]{"1", "0", "First", "Last0", "40", "1993.05.23", "email0@gmail.com", "Male", "Country0", "City0", "Address0", "Zipcode0", "PhoneNumber0", "Department0", "Role0", "2016.07.18", "656", "Active"}));
        usersListInDB.add(new User(new String[]{"2", "7984", "First1", "Last1", "50", "1997.01.05", "email1@gmail.com", "Female", "Country1", "City1", "Address1", "ZipCode1", "PhoneNumber1", "Department", "Role1", "2018.10.08", "778", "Inactive"}));
        usersListInDB.add(new User(new String[]{"3", "5968", "First2", "Last2", "0", "1999.03.20", "email2@gmail.com", "Male", "Country2", "City2", "Address2", "ZipCode2", "PhoneNumber2", "Department2", "Role2", "2019.10.16", "734", "Active"}));
        usersListInDB.add(new User(new String[]{"4", "3952", "First3", "Last3", "10", "1989.04.02", "email3@gmail.com", "Female", "Country3", "City3", "Address3", "ZipCode3", "PhoneNumber3", "Department3", "Role3", "2016.11.05", "858", "Inactive"}));

        List<User> newUsersList = new ArrayList<>();
        newUsersList.add(new User(new String[]{"1", "0", "First0", "Last0", "40", "1993.05.09", "email0@gmail.com", "Male", "Country0", "City0", "Address0", "ZipCode0", "PhoneNumber0", "Department0", "Role0", "2017.02.24", "176", "Active"}));
        newUsersList.add(new User(new String[]{"2", "7984", "First1", "Last1", "50", "1997.01.05", "email1@gmail.com", "Female", "Country1", "City1", "Address1", "ZipCode1", "PhoneNumber1", "Department", "Role1", "2018.10.08", "778", "Inactive"}));

        List<User>[] result = compareUsers(usersListInDB, newUsersList);
        System.out.println("Sample Case 0:");
        System.out.println("Updated Users: " + result[0].size());
        System.out.println("Inserted Users: " + result[1].size());

        // Sample Case 1
        usersListInDB = new ArrayList<>();
        usersListInDB.add(new User(new String[]{"1", "0", "First0", "Last0", "40", "2019.01.26", "email0@gmail.com", "Male", "Country0", "City0", "Address0", "ZipCode0", "PhoneNumber0", "Department0", "Role0", "2018.11.01", "637", "Active"}));
        usersListInDB.add(new User(new String[]{"2", "7984", "First1", "Last1", "50", "1996.08.22", "email1@gmail.com", "Female", "Country1", "City1", "Address1", "ZipCode1", "PhoneNumber1", "Department", "Role1", "2015.02.14", "846", "Inactive"}));
        usersListInDB.add(new User(new String[]{"3", "5968", "First2", "Last2", "0", "1989.08.24", "email2@gmail.com", "Male", "Country2", "City2", "Address2", "ZipCode2", "PhoneNumber2", "Department2", "Role2", "2016.09.17", "508", "Active"}));
        usersListInDB.add(new User(new String[]{"4", "3952", "First3", "Last3", "10", "1994.07.14", "email3@gmail.com", "Female", "Country3", "City3", "Address3", "ZipCode3", "PhoneNumber3", "Department3", "Role3", "2021.07.02", "974", "Inactive"}));

        newUsersList = new ArrayList<>();
        newUsersList.add(new User(new String[]{"0", "0", "First0", "Last0", "40", "2003.01.07", "email0@gmail.com", "Male", "Country0", "City0", "Address0", "ZipCode0", "PhoneNumber0", "Department0", "Role0", "2015.01.11", "989", "Active"}));
        newUsersList.add(new User(new String[]{"0", "9760", "First15", "Last15", "10", "1996.04.11", "email15@gmail.com", "Female", "Country15", "City15", "Address15", "ZipCode15", "PhoneNumber15", "Department15", "Role15", "2017.01.13", "606", "Inactive"}));

        result = compareUsers(usersListInDB, newUsersList);
        System.out.println("\nSample Case 1:");
        System.out.println("Updated Users: " + result[0].size());
        System.out.println("Inserted Users: " + result[1].size());
    }
}
