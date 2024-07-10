package be.hasan.libraSphere.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = IsbnValidator.class)
public @interface ISBN {
    String message() default "The provided ISBN is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
