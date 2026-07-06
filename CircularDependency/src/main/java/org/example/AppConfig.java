package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    // AppConfig is also a component class, as @Configuration itself uses @Component annotation
    // So bean of AppConfig also managed by IoC container
}
