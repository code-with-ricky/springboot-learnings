package com.example.SpringBootDemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo2Application.class, args);
//		ApplicationContext context = SpringApplication.run(SpringBootDemo2Application.class, args);
//		PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);
//		paymentGateway.print();

//		paymentGateway.setType("Paytm");
//		paymentGateway.setRetryCount(5);
//		System.out.println("Payment gateway type: " + paymentGateway.getPaymentType());
//		System.out.println("Payment gateway retry count: " + paymentGateway.getRetryCount());
	}

}
