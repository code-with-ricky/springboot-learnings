package org.example.simple;
public class B {
    A a;
    public B(){
        System.out.println("A created");
        this.a = new A();
    }
}
