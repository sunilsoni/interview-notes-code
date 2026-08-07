package com.interview.notes.code.year.y2026.july.common;

interface NotificationService {
    void send(String message);
}

class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class MessageService {

    private final NotificationService notificationService;

    // Constructor Dependency Injection
    MessageService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    void sendMessage(String message) {
        notificationService.send(message);
    }
}

public class Main {

    public static void main(String[] args) {

        NotificationService email = new EmailNotificationService();
        MessageService emailMessageService = new MessageService(email);
        emailMessageService.sendMessage("Your order is confirmed");

        NotificationService sms = new SMSNotificationService();
        MessageService smsMessageService = new MessageService(sms);
        smsMessageService.sendMessage("Your OTP is 1234");
    }
}