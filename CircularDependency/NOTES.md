# Circular Dependency
The scenario which arises when creation of object of Class A requires object of Class B and also creation of Class B requires object of Class A

![Circular Dependency](images/circulardependency.png)

- its not because of spring, but its because of bad code practice
- check the /src/main/java/org.example/simple
  - we have 2 classes A and B, it goes infinitely creating each other's objects till stack overflow happens
  - this is because circular dependency happened

- In`Spring boot` we get `BeanCurrentlyInCreationException`
---
## Methods to resolve circular dependency in spring
- avoid using `Constructor injection`, rather use `Field Injection` or `Setter Injection`