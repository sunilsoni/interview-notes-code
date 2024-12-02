package com.interview.notes.code.year.y2023.Sep23.google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MessageProcessor1 {

    // HashMap to store the last seen timestamp for each message
    private HashMap<String, Integer> lastSeen = new HashMap<>();

    public static void main(String[] args) {
        MessageProcessor1 processor = new MessageProcessor1();
        List<String> messages = List.of(
                "10 solar panel activated",
                "11 low battery warning",
                "12 tire one: low air pressure",
                "13 solar panel activated",
                "14 low battery warning",
                "21 solar panel activated",
                "35 solar panel activated"
        );

        List<String> output = processor.processMessages(messages);
        for (String msg : output) {
            System.out.println(msg);
        }
    }

    // Function to process a list of messages with timestamps
    public List<String> processMessages(List<String> messages) {
        List<String> result = new LinkedList<>();

        for (String message : messages) {
            String[] parts = message.split(" ", 2);  // Split by the first space
            int timestamp = Integer.parseInt(parts[0]);
            String content = parts[1];

            // Check if message is seen in last 10 seconds
            if (lastSeen.containsKey(content) && timestamp - lastSeen.get(content) <= 10) {
                // If it's a duplicate within the last 10 seconds, we skip
                continue;
            }

            // Update the last seen timestamp for the message
            lastSeen.put(content, timestamp);
            result.add(message);
        }

        return result;
    }
}
