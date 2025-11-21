package com.interview.notes.code.year.y2024.oct24.test6;

import java.util.Random;

public class User {

    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static void main(String[] args) {
        User user = new User("Jack", generatePassword());
        System.out.println("User is " + user.name + " and password is " + user.password);
    }

    private static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPqrstuuvwxyz";
        Integer characterLength = 6;

        String password = "";
        Random random = new Random();
        for (int i = 0; i < characterLength; i++) {
            password += characters.charAt(random.nextInt(characters.length()));
        }
        return password;
    }
}
