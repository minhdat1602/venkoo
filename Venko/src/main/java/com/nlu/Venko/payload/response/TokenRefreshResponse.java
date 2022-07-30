package com.nlu.Venko.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        tokenType = "Bearer";
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
