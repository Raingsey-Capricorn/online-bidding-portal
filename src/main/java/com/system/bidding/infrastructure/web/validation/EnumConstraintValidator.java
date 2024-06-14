package com.system.bidding.infrastructure.web.validation;

import com.system.bidding.infrastructure.annotation.EnumValidate;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Slf4j
public class EnumConstraintValidator implements ConstraintValidator<EnumValidate, Enum<?>> {

    /**
     * @param anEnum                     : enumeration type
     * @param constraintValidatorContext : validation context
     * @return Exception when incompatible type is passing to the method
     */
    @Override
    @SneakyThrows
    public boolean isValid(Enum anEnum, ConstraintValidatorContext constraintValidatorContext) {

        if (anEnum.describeConstable().isPresent() && anEnum.getDeclaringClass().isEnum()) {
            return SecurityConstant.Authority.list()
                    .contains(SecurityConstant.Authority.valueOf(anEnum.name()));
        }
        return true;

    }
}
