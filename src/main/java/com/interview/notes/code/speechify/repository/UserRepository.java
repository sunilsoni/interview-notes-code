package com.interview.notes.code.speechify.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.interview.notes.code.speechify.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserRepository {
    private static final String DB_FILE = "db.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                if (!dbFile.exists()) return new ArrayList<>();

                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode users = (ArrayNode) root.get("users");
                List<User> result = new ArrayList<>();
                for (int i = 0; i < users.size(); i++) {
                    User user = objectMapper.treeToValue(users.get(i), User.class);
                    result.add(user);
                }
                return result;
            } catch (IOException e) {
                return new ArrayList<>();
            }
        });
    }

    public CompletableFuture<User> findByEmail(String email) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                if (!dbFile.exists()) return null;

                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode users = (ArrayNode) root.get("users");

                for (int i = 0; i < users.size(); i++) {
                    User user = objectMapper.treeToValue(users.get(i), User.class);
                    if (user.getEmail().equals(email)) {
                        return user;
                    }
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        });
    }

    public CompletableFuture<User> save(User user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode users = (ArrayNode) root.get("users");

                users.add(objectMapper.valueToTree(user));
                objectMapper.writeValue(dbFile, root);
                return user;
            } catch (IOException e) {
                return null;
            }
        });
    }

    public CompletableFuture<Boolean> update(User user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode users = (ArrayNode) root.get("users");

                for (int i = 0; i < users.size(); i++) {
                    User existing = objectMapper.treeToValue(users.get(i), User.class);
                    if (existing.getId().equals(user.getId())) {
                        users.set(i, objectMapper.valueToTree(user));
                        objectMapper.writeValue(dbFile, root);
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                return false;
            }
        });
    }
}
