package com.system.bidding.infrastructure.web.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record JwtAuthenticationResponse(
        @Schema(name = "token", description = "Access Token")
        String token) {
}
