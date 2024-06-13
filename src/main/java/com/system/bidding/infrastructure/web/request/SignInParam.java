package com.system.bidding.infrastructure.web.request;

import com.system.bidding.infrastructure.annotation.StringValidate;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record SignInParam(
        @StringValidate(type = StringValidate.FieldType.EMAIL, fieldName = "email")
        String email,
        @StringValidate(type = StringValidate.FieldType.PASSWORD, fieldName = "password")
        String password) implements Serializable {
}
