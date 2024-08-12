package com.interview.notes.code.months.aug24.test17;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class User {
    private int id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String department;
    private String roles;
    private LocalDate joinDate;
    private double credit;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIdentityNumber() { return identityNumber; }
    public void setIdentityNumber(String identityNumber) { this.identityNumber = identityNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User(String[] data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        setId(Integer.parseInt(data[0]));
        setIdentityNumber(data[1]);
        setFirstName(data[2]);
        setLastName(data[3]);
        setAge(Integer.parseInt(data[4]));
        setBirthDate(LocalDate.parse(data[5], formatter));
        setEmail(data[6]);
        setGender(data[7]);
        setCountry(data[8]);
        setCity(data[9]);
        setAddress(data[10]);
        setZipCode(data[11]);
        setPhoneNumber(data[12]);
        setDepartment(data[13]);
        setRoles(data[14]);
        setJoinDate(LocalDate.parse(data[15], formatter));
        setCredit(Double.parseDouble(data[16]));
        setStatus(data[17]);
    }

    @Override
    public String toString() {
        return getId() + " " + getFirstName() + " " + getLastName();
    }
}

class UserManager {
    public static List<User>[] compareUsers(List<User> usersListInDB, List<User> newUsersList) {
        List<User> updatedUsers = new ArrayList<>();
        List<User> insertedUsers = new ArrayList<>();

        Map<Integer, User> existingUsers = new HashMap<>();
        for (User user : usersListInDB) {
            existingUsers.put(user.getId(), user);
        }

        for (User newUser : newUsersList) {
            if (newUser.getId() == 0) {
                insertedUsers.add(newUser);
            } else {
                User existingUser = existingUsers.get(newUser.getId());
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
        return existingUser.getIdentityNumber().equals(newUser.getIdentityNumber()) &&
               existingUser.getFirstName().equals(newUser.getFirstName()) &&
               existingUser.getLastName().equals(newUser.getLastName()) &&
               existingUser.getAge() == newUser.getAge() &&
               existingUser.getBirthDate().equals(newUser.getBirthDate()) &&
               existingUser.getEmail().equals(newUser.getEmail()) &&
               existingUser.getGender().equals(newUser.getGender()) &&
               existingUser.getCountry().equals(newUser.getCountry()) &&
               existingUser.getCity().equals(newUser.getCity()) &&
               existingUser.getAddress().equals(newUser.getAddress()) &&
               existingUser.getZipCode().equals(newUser.getZipCode()) &&
               existingUser.getPhoneNumber().equals(newUser.getPhoneNumber()) &&
               existingUser.getDepartment().equals(newUser.getDepartment()) &&
               existingUser.getRoles().equals(newUser.getRoles()) &&
               existingUser.getJoinDate().equals(newUser.getJoinDate()) &&
               existingUser.getCredit() == newUser.getCredit() &&
               existingUser.getStatus().equals(newUser.getStatus());
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
