package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    OrderService orderService;
//    public PaymentService(OrderService orderService){
//        this.orderService = orderService;
//    }

    public void pay(){
        System.out.println("Payment Done!");

        // following should not be responsibility of payment service
        // must following Single Responsibility Design principal
        // orderService.getOrderDetails();
    }
}
