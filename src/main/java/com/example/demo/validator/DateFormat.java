package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateFormatConstrainValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

    // error message
    public String message() default "Invalid date";

    //represents group of constraints
    public Class<?>[]groups() default{};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default{};
}
