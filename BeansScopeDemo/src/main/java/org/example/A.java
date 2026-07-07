package org.example;

import org.springframework.stereotype.Component;

@Component
public class A {
    OrderService orderService;
    public A(OrderService orderService){
        this.orderService = orderService;
    }
}
