package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.annotation.StringValidate;
import com.system.bidding.infrastructure.config.constants.SecurityConstant;
import lombok.Builder;

import java.io.Serializable;
import java.util.Optional;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@Builder
public record SignUpParam(

        @StringValidate(type = StringValidate.FieldType.STRING, fieldName = "First Name")
        String firstName,

        @StringValidate(type = StringValidate.FieldType.STRING, fieldName = "Last Name")
        String lastName,

        @StringValidate(type = StringValidate.FieldType.STRING, fieldName = "UserName")
        String userName,

        @StringValidate(type = StringValidate.FieldType.EMAIL, fieldName = "Email")
        String email,

        Optional<String> password,
        Optional<SecurityConstant.AuthorizationRole> role,
        Optional<SecurityConstant.AuthorizationProvider> authorizationProvider) implements Serializable {
}