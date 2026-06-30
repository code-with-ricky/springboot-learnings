package org.example;

import org.example.notification.EmailService;
import org.example.notification.NotificationService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        NotificationService notification = new EmailService();
        OrderService order = new OrderService(notification);
        order.placeOrder();
    }
}

//IOC -> Inversion of  control
// initial design: OrderService --Created--> EmailService
// After DI: Main ---Provides--> OrderService

// IOC is a principle
// DI is approach/technique to achieve Ioc

// In Spring Framework --> IOC container
// IOC container does following tasks:
// 1. Create objects
// 2. Manage Objects
// 3. Connects object together

// Here Main is doing the work of IoC container

// Java code --> Objects
// Spring IoC container --> contains objects which are called beans

// all beans are objects, but all objects are not beans
// objects stored and managed by IoC container are called beans