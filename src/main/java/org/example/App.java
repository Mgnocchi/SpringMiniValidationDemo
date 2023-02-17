package org.example;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Set;

/**
 * This is not how you use this stuff, this is more how Spring does
 */

@Configuration
@ComponentScan
public class App {

    @Bean
    public String getName() {
        return "";
    }

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Student student = context.getBean(Student.class);
        student.printName();
        // jakarta implementation
        System.out.println("Validating an existing bean, that didn't complain during creation");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        for (ConstraintViolation<Student> violation : violations) {
            System.out.println((violation.getMessage()));
        }

        for (String str : context.getBeanDefinitionNames()) {
            System.out.println(str + " " + context.getBean(str).getClass() );
        }


        // Spring implementation: I believe this is an approximation of the process that is made under the hood during controller validation
        System.out.println("Experimenting with an empty student-like bean");
        postStudent postStudent = context.getBean(org.example.postStudent.class);

        DataBinder binder = new DataBinder(postStudent);
        MutablePropertyValues postStudentProperties = new MutablePropertyValues();
        postStudentProperties.add("name","sgfdg");
        binder.addValidators(new org.springframework.validation.Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return postStudent.class.isAssignableFrom(clazz);
            }
            @Override
            public void validate(Object target, Errors errors) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors,"Name","field.required","Name can't be blank");
            }
        });
        binder.bind(postStudentProperties);
        binder.validate();
        System.out.println(binder.getBindingResult());
        postStudent.printName();
    }
}
