package org.example.simple;

public class A {
    B b;
    public A(){
        System.out.println("B created");
        this.b = new B();
    }
}
