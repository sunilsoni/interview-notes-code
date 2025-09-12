package com.interview.notes.code.speechify.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.interview.notes.code.speechify.model.Client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClientRepository {
    private static final String DB_FILE = "db.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletableFuture<Client> findById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                if (!dbFile.exists()) return null;

                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode clients = (ArrayNode) root.get("clients");

                for (int i = 0; i < clients.size(); i++) {
                    Client client = objectMapper.treeToValue(clients.get(i), Client.class);
                    if (client.getId().equals(id)) {
                        return client;
                    }
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        });
    }

    public CompletableFuture<List<Client>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                File dbFile = new File(DB_FILE);
                if (!dbFile.exists()) return new ArrayList<>();

                ObjectNode root = (ObjectNode) objectMapper.readTree(dbFile);
                ArrayNode clients = (ArrayNode) root.get("clients");
                List<Client> result = new ArrayList<>();

                for (int i = 0; i < clients.size(); i++) {
                    Client client = objectMapper.treeToValue(clients.get(i), Client.class);
                    result.add(client);
                }
                return result;
            } catch (IOException e) {
                return new ArrayList<>();
            }
        });
    }
}
