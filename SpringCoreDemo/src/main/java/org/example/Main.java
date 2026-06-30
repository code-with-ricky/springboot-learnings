package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Ioc Container, with annotation based config, is up
        // with metadata of AppConfig class --> having rules for IoC container
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // now after this line get executed, an IoC container would have created having beans of OrderService and PaymentService
        // and the IoC container is stored in ApplicationContext context;
        // if I want bean of any class then i can use getBeans() method as below

        OrderService order = context.getBean(OrderService.class);
        order.placeOrder();

        // User user = context.getBean(User.class);
        // System.out.println(user.getName());
    }
}

// Reflection API
// Class<Student> c1 = Student.class;
// c1 is a special object, which holds the metadata of class Student:
// -> class name: Student
// -> fields: name, rollNo
// -> constructor: Student()
// -> methods:
// -> annotations:
// etc

//class Student {
//    private String name;
//    private int rollNo;
//
//    public Student() {
//
//    }
//
//}