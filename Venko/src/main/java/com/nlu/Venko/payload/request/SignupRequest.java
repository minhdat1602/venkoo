package com.nlu.Venko.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignupRequest {

    private String username;
    private String fullname;
    private String email;
    private String password;
    private Set<String> role;
}
