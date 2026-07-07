package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // we can explicitly mention as well
public class OrderService {
    public OrderService(){
        System.out.println("Order Service Created");
    }

    public void placeOrder(){
        System.out.println("Order Placed");
    }
}
