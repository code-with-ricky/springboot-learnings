package org.example;

import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// class which are needed to be handled by spring ioc container are having annotation Component
@Component
public class OrderService {
    // Field Injection
//    @Autowired
//    private PaymentService payment;


    // Setter Injection
    // public void setPaymentService(PaymentService payment) {
    // this.payment = payment;
    // }

    private final PaymentService payment;

    // @Autowired will wire (provide) object of PaymentService automatically by spring
    // when it will be, as passed in constructor so when ObjectService class's object is created
    // this constructor injection (type of dependency injection)
    // preferred is this type of DI
    @Autowired
    public OrderService(PaymentService payment){
        this.payment = payment;
    }

    // note if only one constructor is there, we can also avoid writing @Autowired annotation; only in case of Constructor injection
    // Why constructor injection is preferred
    // -> Dependency get wired at time of object creation
    // -> we can use final keyword
    // -> very helpful for Unit Testing

    public void placeOrder() {
        payment.pay();
        System.out.println("Order Placed");
    }
}
