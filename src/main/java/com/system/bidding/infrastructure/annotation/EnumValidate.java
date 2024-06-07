package com.system.bidding.infrastructure.annotation;

import com.system.bidding.infrastructure.web.validation.EnumConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Documented
@Target({ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE,
        ElementType.METHOD,
        ElementType.FIELD,
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumConstraintValidator.class)
public @interface EnumValidate {
    Class<? extends Payload>[] payload() default {};

    String fieldName();

    String message() default " is invalid";

    Class<?>[] groups() default {};
}
