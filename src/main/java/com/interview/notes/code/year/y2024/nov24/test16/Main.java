package com.interview.notes.code.year.y2024.nov24.test16;

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(30)
                .setEmail("john.doe@example.com")
                .setPhoneNumber("123-456-7890")
                .build();

        System.out.println(user);
    }
}
