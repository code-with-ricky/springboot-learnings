package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService1 = context.getBean(OrderService.class);
        OrderService orderService2 = context.getBean(OrderService.class);

        // check if both have same object or different
        System.out.println(orderService1 == orderService2); // true
    }
}