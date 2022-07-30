package com.nlu.Venko.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class JwtResponse {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.tokenType = "Bearer";
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles) {
        this(accessToken, id, username, email, roles);
        this.refreshToken = refreshToken;
    }
}
