package org.example.notification;

public class PopupNotification implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Pop-up notification sent ");
    }
}
