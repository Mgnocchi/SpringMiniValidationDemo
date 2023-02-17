package org.example;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class PostStudent {
    @NotBlank(message = "name can't be blank")
    String Name;

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public void printName() {
        System.out.println("name is: " + this.Name);
    }
}
