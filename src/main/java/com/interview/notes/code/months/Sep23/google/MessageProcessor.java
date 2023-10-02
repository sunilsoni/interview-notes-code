package com.interview.notes.code.months.Sep23.google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 In Java
 // Hi, my name is Grant
 /*
 Imagine you have a robot that sends status messages that humans will read in real time.
 The raw messages are hard to read for a human because there are often many messages produced in short periods of time.
 One idea to make them more readable is to remove the duplicate messages over a sliding 10 second window.
 Design and implement a program to hide duplicates of any message that has already been displayed within the past 10 seconds.

 Example Messages Received, with Timestamps:

 10 solar panel activated
 11 low battery warning
 12 tire one: low air pressure
 13 solar panel activated
 14 low battery warning
 21 solar panel activated
 35 solar panel activated
 Example Messages Shown to User:
 10  solar panel activated
 11  low battery warning
 12 tire one: low air pressure
 21 solar panel activated
 35 solar panel activated


 */
public class MessageProcessor {
    private final StatusReceiver receiver;
    // To store the latest timestamp of each message
    private final HashMap<String, Integer> messageTimestamps;
    // To store messages in order they are received
    private final Queue<Status> messageQueue;

    public MessageProcessor(StatusReceiver receiver) {
        this.receiver = receiver;
        this.messageTimestamps = new HashMap<>();
        this.messageQueue = new LinkedList<>();
    }

    public Status getNextReadableMessage() {
        while (true) {
            Status rawMessage = receiver.getRawMessage();

            // Remove messages older than 10 seconds from the queue and the hashmap
            while (!messageQueue.isEmpty() && messageQueue.peek().timestamp <= rawMessage.timestamp - 10) {
                Status oldStatus = messageQueue.poll();
                messageTimestamps.remove(oldStatus.message);
            }

            // If the message hasn't been seen in the last 10 seconds
            if (!messageTimestamps.containsKey(rawMessage.message) || messageTimestamps.get(rawMessage.message) <= rawMessage.timestamp - 10) {
                // Update the timestamp in the hashmap
                messageTimestamps.put(rawMessage.message, rawMessage.timestamp);
                // Add to the queue
                messageQueue.add(rawMessage);
                return rawMessage;
            }

            // If it's a duplicate within the last 10 seconds, just add to the queue without returning
            messageQueue.add(rawMessage);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a mock StatusReceiver
        StatusReceiver mockReceiver = new StatusReceiver() {
            private int index = 0;
            private Status[] statuses = {
                    new Status(10, "solar panel activated"),
                    new Status(11, "low battery warning"),
                    new Status(12, "tire one: low air pressure"),
                    new Status(13, "solar panel activated"),
                    new Status(14, "low battery warning"),
                    new Status(21, "solar panel activated"),
                    new Status(35, "solar panel activated")
            };

            @Override
            public Status getRawMessage() {
                if (index < statuses.length) {
                    return statuses[index++];
                }
                // Simulating waiting for a message using Thread.sleep
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;  // Return null when no more messages
            }
        };

        MessageProcessor processor = new MessageProcessor(mockReceiver);
        for (int i = 0; i < 7; i++) {  // Assuming we have 7 messages
            Status readableMessage = processor.getNextReadableMessage();
            if (readableMessage != null) {
                System.out.println(readableMessage.toString());
            }
        }
    }

}
