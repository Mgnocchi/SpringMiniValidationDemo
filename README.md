# Validation in Isolation

The repo contains a small Spring-core application that produces two beans and tests two different manual validation 
methods.

## Jakarta
The first one involves the `Student.java` class, which produces a constructor injected bean in a class annotated for
validation according to the jakartaEE standard libraries. The validation is carried on in a previously set up object
using `jakarta.validation.Validator`

## Spring
The second one is applied on the bean produced from the `PostStudent.java` class. It uses the Spring validation APIs 
in a fashion that is more similar to what happens in a Spring MVC setting. In this case the validation still happens 
after the injection of the dependency, and it relies directly on the annotations present in the target class.
Given the regularity of the validation process, though, I believe that the Spring Framework is able to pre-process those
annotations and produce the needed validator, in an MVC setting.

Important to notice that in an MVC context, validation performed automatically does the same thing: the value is 
injected in the Bean and only after it is validated.