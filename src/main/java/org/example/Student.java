package org.example;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;


@Component
public class Student {
    @NotBlank(message = "name can't be blank")
    String Name;

    public Student(String name) {
        Name = name;
    }

    public void printName() {
        System.out.println("name is: " + this.Name);
    }
}
