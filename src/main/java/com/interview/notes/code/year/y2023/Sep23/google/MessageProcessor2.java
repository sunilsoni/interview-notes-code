package com.interview.notes.code.year.y2023.Sep23.google;

import java.util.HashMap;

class MessageProcessor2 implements Runnable {
    private StatusReceiver receiver;
    private HashMap<String, Integer> lastSeen;

    public MessageProcessor2(StatusReceiver receiver) {
        this.receiver = receiver;
        this.lastSeen = new HashMap<>();
    }

    public static void main(String[] args) {
        // Example implementation of the StatusReceiver for demonstration
        StatusReceiver receiver = new StatusReceiver() {
            private int counter = 0;
            private String[] testMessages = {
                    "solar panel activated",
                    "low battery warning",
                    "tire one: low air pressure",
                    "solar panel activated",
                    "low battery warning",
                    "solar panel activated",
                    "solar panel activated"
            };
            private int[] testTimestamps = {10, 11, 12, 13, 14, 21, 35};

            @Override
            public Status getRawMessage() {
                if (counter < testMessages.length) {
                    return new Status(testTimestamps[counter], testMessages[counter++]);
                }
                // Simulating a blocking call here
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        MessageProcessor2 processor = new MessageProcessor2(receiver);
        new Thread(processor).start();
    }

    public void run() {
        while (true) {
            Status status = receiver.getRawMessage();

            if (shouldDisplay(status)) {
                System.out.println(status.toString());
                lastSeen.put(status.message, status.timestamp);
            }
        }
    }

    private boolean shouldDisplay(Status status) {
        if (status != null && status.message != null) {
            // Access the message field or do further processing
        } else {
            // Handle the scenario where status or its message field is null
        }

        return !lastSeen.containsKey(status.message) || (status.timestamp - lastSeen.get(status.message) > 10);
    }
}