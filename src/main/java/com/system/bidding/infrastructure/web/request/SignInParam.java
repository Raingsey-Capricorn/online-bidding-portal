package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.annotation.StringValidate;
import lombok.Builder;

import java.io.Serializable;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 *
 * @param email    : email
 * @param password : password
 */
@Builder
public record SignInParam(
        @StringValidate(type = StringValidate.FieldType.EMAIL, fieldName = "email")
        String email,
        @StringValidate(type = StringValidate.FieldType.PASSWORD, fieldName = "password")
        String password) implements Serializable {
}
