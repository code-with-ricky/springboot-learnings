## Steps for ApplicationContext (IoC Container)

- Step 1: Spring start the container
- Step 2: Spring reads `AppConfig.java`
- Step 3: Spring processes `@ComponentScan`
- Step 5: Spring creates `bean definitions` (meta data)
  - eg: Bean name, Bean Class, Scope, Dependency: no etc
  - an interface is there named BeanDefinition
- Step 6: Spring starts `creating object`
  - PaymentService paymentService = new PaymentService();
  - OrderService orderService = new OrderService(paymentService);
  - both are not wired together
- Step 7: Our application uses the beans

### What if I have multiple bean of PaymentService ?
- Which one to pass? --> expected 1, got 2, so confused
- two annotations are there -> `@Primary`, `@Qualifier`
- @Primary is to be attached only one, that will be passed

```java
import org.example.payment.PaymentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
class CardPayment extends PaymentService {
    @Override
    public void pay() {
        System.out.println("Card Payment Done");
    }
}
```

- If using @Qualifier, then attach it in all PaymentService classes and in constructor, infront of tha parameter use @Qualifier("bean-name")
- bean name is same as the class name by default but in camelCase

```java
// CardPayment.java
import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
class CardPayment extends PaymentService {
    @Override
    public void pay() {
        System.out.println("Card Payment Done");
    }
}
```

```java
// UpiPayment.java
import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
class UpiPayment extends PaymentService {
    @Override
    public void pay() {
        System.out.println("UPI Payment Done");
    }
}
```

```java
// OrderService.java

import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class OrderService {
  private final PaymentService payment;

  @Autowired
  public OrderService(@Qualifier("cardPayment") PaymentService payment) {
    this.payment = payment;
  }
}
```

- we can also provide our custom name to be used for qualifier

```java
 import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("card")
class CardPayment implements PaymentService {
}

@Component
@Qualifier("upi")
class UpiPayment implements PaymentService {
}

class OrderService {
    
    private final PaymentService payment;
    
  @Autowired
  public OrderService(@Qualifier("upi") PaymentService payment){
      this.payment = payment;
  }
}
```

---

## When @Component can fail ?
- When it's not possible to add @Component on class
  - suppose we have a jar file as dependency, inside which we have CartService class and methods
  - those classes are read only and cannot be edited to add @Component

- When it's complicated to create bean automatically by spring
  - suppose the class's constructor asking for some values like name, age etc
  - Spring cannot automatically provide values for those

### What's Solution? - @Bean
- @Bean annotation is used on the methods which create objects and provide
- but by using the annotation the object become bean and is handled by spring
- We did in `AppConfig.java`

```java
// AppConfig.java

import org.example.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
class AppConfig {
  @Bean
  public User createUser() {
    return new User("Amrik", 34);
  }
}
```

> NOTE: we can use @Bean on class as well; for CardPayment, UpiPayment, etc

```java
// CardPayment.java
import org.example.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

class CardPayment implements PaymentService {
}


// UpiPayment.java
class UpiPayment implements PaymentService {
}

// OrderService.java
class OrderService {
  private final PaymentService payment;

  @Autowired
  public OrderService(PaymentService payment) {
      this.payment = payment;
  }
}
```

```java
// AppConfig.java

import org.example.OrderService;
import org.example.payment.CardPayment;
import org.example.payment.PaymentService;
import org.example.payment.UpiPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
class AppConfig {
  @Bean
  @Primary
  public PaymentService createCardPayment() {
    return new CardPayment();
  }

  @Bean
  public PaymentService createUpiPayment() {
    return new UpiPayment();
  }

  @Bean
  public OrderService createOrderService(PaymentService payment){
      return new OrderService(payment);
  }
}
```

> NOTE: if we use both @Bean and @Component, then priority is given to @Bean
---

## ApplicationContext vs BeanFactory
- BeanFactory is also an interface which was earlier used same as ApplicationContext interface, for storing beans