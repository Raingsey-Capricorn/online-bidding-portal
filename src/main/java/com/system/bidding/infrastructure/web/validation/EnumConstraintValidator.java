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
     * @param anEnum
     * @param constraintValidatorContext
     * @return
     */
    @Override
    @SneakyThrows
    public boolean isValid(Enum anEnum, ConstraintValidatorContext constraintValidatorContext) {

        if (anEnum.describeConstable().isPresent() && anEnum.getDeclaringClass().isEnum()) {
            return SecurityConstant.AuthorizationRole.list()
                    .contains(SecurityConstant.AuthorizationRole.valueOf(anEnum.name()));
        }
        return true;

    }
}
