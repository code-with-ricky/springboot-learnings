package org.example;

import org.springframework.stereotype.Component;

@Component
public class B {
    OrderService orderService;
    public B(OrderService orderService){
        this.orderService = orderService;
    }
}
