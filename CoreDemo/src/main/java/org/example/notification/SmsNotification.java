package org.example.notification;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Sms notification sent ");
    }
}
