package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
//    @Value("${paymentGateway.type}")
//    private String type;
//    @Value("${paymentGateway.retryCount}")
//    private int retryCount;

//    public PaymentGateway(@Value("${paymentGateway.type}") String type,
//                          @Value("${paymentGateway.retryCount}") int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }

    @Autowired
    private PaymentProperties paymentProperties;

    public String getPaymentType() {
        return paymentProperties.getType();
    }

    public int getRetryCount() {
        return paymentProperties.getRetryCount();
    }

    public int getTimeout() {
        return paymentProperties.getTimeout();
    }

    public boolean getIsEnabled() {
        return paymentProperties.isEnabled();
    }

    public void print() {
        System.out.println("Payment gateway type: " + getPaymentType());
        System.out.println("Payment gateway retry count: " + getRetryCount());
        System.out.println("Payment gateway enabled: " + getIsEnabled());
        System.out.println("Payment gateway timeout: " + getTimeout());
    }

//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setRetryCount(int retryCount) {
//        this.retryCount = retryCount;
//    }
}
