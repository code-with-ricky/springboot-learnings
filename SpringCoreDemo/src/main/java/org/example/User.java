package org.example;

import org.springframework.stereotype.Component;

//@Component --> here it cant be used, as in the constructor, its asking for user name and age
// spring cannot automatically assign those values
// so bean creation can be done using @Bean annotation
// go to AppConfig

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
