package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")   // scan inside the provided package what all classes are there having @Component annotation, which are to be handled by spring
// can we have @ComponentScan without providing package name? Yes in that case it will scan only that package in which AppConfig.java is
public class AppConfig {

    // Bean annotation is used with methods
    // we create object and provide it to spring to handle it
    // @Bean annotation tells Spring, that when you will be reading AppConfig rules, call the methods to create object
    @Bean
    public User createUser() {
        return new User("Amrik", 23);
    }
}
