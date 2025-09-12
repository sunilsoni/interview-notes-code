package com.interview.notes.code.speechify.service;

import com.interview.notes.code.speechify.model.Client;
import com.interview.notes.code.speechify.model.User;
import com.interview.notes.code.speechify.repository.ClientRepository;
import com.interview.notes.code.speechify.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserService {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public UserService(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    public CompletableFuture<Boolean> addUser(
            String firstname, String surname, String email, LocalDate dob, String clientId) {
        return CompletableFuture.supplyAsync(() -> {
            if (firstname == null || surname == null || email == null) return false;

            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 21) return false;

            Client client = clientRepository.findById(clientId).join();
            if (client == null) return false;

            // prevent duplicates
            User existing = userRepository.findByEmail(email).join();
            if (existing != null) return false;

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setClient(client);
            user.setDateOfBirth(dob);
            user.setEmail(email);
            user.setFirstname(firstname);
            user.setSurname(surname);

            if ("VeryImportantClient".equals(client.getName())) {
                user.setHasCreditLimit(false);
            } else if ("ImportantClient".equals(client.getName())) {
                user.setHasCreditLimit(true);
                user.setCreditLimit(20000);
            } else {
                user.setHasCreditLimit(true);
                user.setCreditLimit(10000);
            }

            return userRepository.save(user).join() != null;
        });
    }

    public CompletableFuture<Boolean> updateUser(User user) {
        return userRepository.update(user);
    }

    public CompletableFuture<List<User>> getAllUsers() {
        return userRepository.findAll();
    }

    public CompletableFuture<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
