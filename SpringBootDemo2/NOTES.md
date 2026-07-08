# Spring Boot Demo 2

## Without application.properties
- suppose we have created a PaymentGateway class and inside we have a type and retry count
- for now we have set and get its value in main file
```java
package com.example.SpringBootDemo2;

import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    private int retryCount;
}
```
```java
package com.example.SpringBootDemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemo2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemo2Application.class, args);
		PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);
		paymentGateway.setType("Paytm");
		paymentGateway.setRetryCount(5);
		System.out.println("Payment gateway type: " + paymentGateway.getType());
		System.out.println("Payment gateway retry count: " + paymentGateway.getRetryCount());
	}

}
```
> Issue: if later if i need to change type and retryCount i need to do changes in code and re-deploy the new code

> What if there is a solution, where i can provide these configuration values from outside and without changing code can change the configuration values?
>  **Solution:** `application.properties`

---

## Understanding `application.properties`
- It is a non-java file, and it gets automatically loaded when application runs
- It has `key:value` kind of structure
- Location: `src/main/resources/application.properties`
- Initially it has following content in it:
```text
# application.properties
spring.application.name=SpringBootDemo2
```
- Other external files like these are:
  - application.yaml
  - environment variables
  - command line argument
  - system properties

> First add type and retryCount in application.properties
```text
paymentGateway.type=paytm
paymentGateway.retryCount=5
```

> Now in the PaymentGateway class we will use `@Value` annotation to assign the property values to variables
```java
package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
    private String type;
    private int retryCount;
    
    // in constructor used the values from application.properties
    public PaymentGateway(@Value("${paymentGateway.type}") String type,
                          @Value("${paymentGateway.retryCount}") int retryCount) {
        this.type = type;
        this.retryCount = retryCount;
    }

    public String getType() {
      return type;
    }
  
    public int getRetryCount() {
      return retryCount;
    }
}
```
> We can do without using constructor and directly using @Values to the fields
```java
package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
    @Value("${paymentGateway.type}")
    private String type;
    
    @Value("${paymentGateway.retryCount}")
    private int retryCount;

    public String getType() {
      return type;
    }
  
    public int getRetryCount() {
      return retryCount;
    }
}
```

> Suppose we forgot to mention any property in application.properties, then following error comes: `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'paymentGateway': Injection of autowired dependencies failed` and `org.springframework.util.PlaceholderResolutionException: Could not resolve placeholder 'paymentGateway.type' in value "${paymentGateway.type}"`

Therefore we can add default values also
```java
package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
  @Value("${paymentGateway.type}:RazorPay")
  private String type;

  @Value("${paymentGateway.retryCount}:5")
  private int retryCount;
}
```
> **Note:** If i don't want to write @Value each time, we can create a Configuration property class and there we can map variables from application.properties

```text
payment-property.type=paytm
payment-property.retry-count=5
payment-property.is-enabled=true
payment-property.timeout=3000
```
```java
package com.example.SpringBootDemo2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("payment-property") // collect all those whose prefix is "payment-property"
public class PaymentProperties {
    private String type;
    private int retryCount;
    private boolean isEnabled;
    private int timeout;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
```
```java
package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
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
}
```
> **Note:** In application.properties, we write in canonical form, and in java we use camelCasing, so Spring boot automatically handles this.
---

## Understanding ApplicationRunner interface
- Currently we were collection ApplicationContext in main class, and manually getting the beans and running print method
- But in Spring boot its not correct way
- We need that when we start our application, the method should called automatically
- So we will create our runner class and there we will implement `ApplicationRunner` interface.

```java
// DemoRunner.java
package com.example.SpringBootDemo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements ApplicationRunner {
  @Autowired
  private final PaymentGateway paymentGateway;

  public DemoRunner(PaymentGateway paymentGateway){
    this.paymentGateway = paymentGateway;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    paymentGateway.print();
  }
}
```

or we can implement `CommandLineRunner` interface, it will work in same manner
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public  class DemoRunner implements CommandLineRunner {
    @Autowired
    private final PaymentGateway paymentGateway;
    
    public DemoRunner(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    
    @Override
    public void run(String... args) throws Exception {
        paymentGateway.print();
    }
}
```
Run the following command to run the application
```bash
mvn spring-boot:run
```