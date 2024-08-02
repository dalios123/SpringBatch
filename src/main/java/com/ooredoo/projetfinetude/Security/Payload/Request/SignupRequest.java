package com.ooredoo.projetfinetude.Security.Payload.Request;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
public class SignupRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private Set<String> role;
    private String password;
}
