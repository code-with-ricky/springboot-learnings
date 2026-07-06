package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    PaymentService paymentService;

//    public OrderService(PaymentService paymentService){
//        this.paymentService = paymentService;
//    }

    public void placeOrder(){
        paymentService.pay();
        System.out.println("Order Placed");
    }
    public void getOrderDetails() {
        System.out.println("Order Details");
    }
}
