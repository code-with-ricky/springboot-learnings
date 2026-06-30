package org.example;

import org.example.notification.EmailService;
import org.example.notification.NotificationService;

public class OrderService {
    // Dependency Injection
    // don't create your dependency, get it from somewhere
    NotificationService notification;

    OrderService(NotificationService notification) {
        this.notification = notification;
    }

    public void placeOrder() {
        System.out.println("Order Placed");
        notification.sendNotification();
    }
}
