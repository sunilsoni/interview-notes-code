package com.interview.notes.code.speechify.model;

import java.time.LocalDate;

public class User {
    private String id;
    private Client client;
    private LocalDate dateOfBirth;
    private String email;
    private String firstname;
    private String surname;
    private boolean hasCreditLimit;
    private double creditLimit;

    public User() {
    }

    public User(String id, Client client, LocalDate dateOfBirth, String email,
                String firstname, String surname, boolean hasCreditLimit, double creditLimit) {
        this.id = id;
        this.client = client;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.hasCreditLimit = hasCreditLimit;
        this.creditLimit = creditLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isHasCreditLimit() {
        return hasCreditLimit;
    }

    public void setHasCreditLimit(boolean hasCreditLimit) {
        this.hasCreditLimit = hasCreditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
