package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.annotation.EnumValidate;
import com.system.bidding.infrastructure.annotation.StringValidate;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import lombok.Builder;

import java.io.Serializable;

/**
 * LinkedIn : https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Builder
public record SignUpParam(
        @StringValidate(type = StringValidate.FieldType.STRING, fieldName = "First Name")
        String firstName,
        @StringValidate(type = StringValidate.FieldType.STRING, fieldName = "Last Name")
        String lastName,
        @StringValidate(type = StringValidate.FieldType.EMAIL, fieldName = "Email")
        String email,
        @StringValidate(type = StringValidate.FieldType.PASSWORD, fieldName = "Password")
        String password,
        @EnumValidate(fieldName = "Password")
        SecurityConstant.AuthorizationRole role) implements Serializable {
}