package com.ooredoo.projetfinetude.Controllers;

import com.ooredoo.projetfinetude.Entities.LoginResponse;
import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Services.Impl.AuthenticationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AthenticationController {
    @Autowired
    private AuthenticationImpl authentication;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user){
        return  authentication.login(user);
    }


}
