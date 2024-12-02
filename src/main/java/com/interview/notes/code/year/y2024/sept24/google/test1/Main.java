package com.interview.notes.code.year.y2024.sept24.google.test1;

import java.util.*;

/*
• Imagine you have a robot that sends status messages that humans will read in real time.
• The raw messages are hard to read for a human because there are often many messages produced in short periods of time. One idea to make them more readable is to remove the duplicate messages over a sliding 10 second window.
• Design and implement a program to hide duplicates of any message that has already been displayed within the past 10 seconds.
• Example Messages Received, with Timestamps:
• 10 solar panel activated
11 low battery warning
• 12 tire one: low air pressure
• 13 solar panel activated
• 14 low battery warning
• 21 solar panel activated
• 35 solar panel activated
• Example Messages Shown to User:
• 10 solar panel activated
11 low battery warning
• 12 tire one: low air pressure
• 21 solar panel activated
• 35 solar panel activated
 */
public class Main {
    public static void main(String[] args) {
        List<Message> messages = Arrays.asList(
                new Message(10, "solar panel activated"),
                new Message(11, "low battery warning"),
                new Message(12, "tire one: low air pressure"),
                new Message(13, "solar panel activated"),
                new Message(14, "low battery warning"),
                new Message(21, "solar panel activated"),
                new Message(35, "solar panel activated")
        );

        List<Message> displayedMessages = processMessages(messages);

        System.out.println("Displayed Messages:");
        for (Message msg : displayedMessages) {
            System.out.println(msg.timestamp + " " + msg.content);
        }
    }

    public static List<Message> processMessages(List<Message> messages) {
        List<Message> displayedMessages = new ArrayList<>();
        Queue<Message> window = new LinkedList<>();
        Set<String> uniqueMessages = new HashSet<>();

        for (Message message : messages) {
            // Remove outdated messages
            while (!window.isEmpty() && message.timestamp - window.peek().timestamp > 10) {
                Message oldMessage = window.poll();
                uniqueMessages.remove(oldMessage.content);
            }

            // Check if message is unique in the current window
            if (!uniqueMessages.contains(message.content)) {
                displayedMessages.add(message);
                window.offer(message);
                uniqueMessages.add(message.content);
            }
        }

        return displayedMessages;
    }

    static class Message {
        int timestamp;
        String content;

        Message(int timestamp, String content) {
            this.timestamp = timestamp;
            this.content = content;
        }
    }
}
