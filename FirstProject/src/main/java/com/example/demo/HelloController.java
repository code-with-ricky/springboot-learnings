package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("")
    public String welcome() {
        return "<h1>Welcome to my Spring Boot application</h1>";
    }

    // GET /hello
    @GetMapping("hello")
    public String greetHello() {
        return "<h1>Hello World</h1>";
    }

    // GET /bye
    @GetMapping("bye")
    public String greetBye(){
        return "<h1>Bye</h1>";
    }
}
