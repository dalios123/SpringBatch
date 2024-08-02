package com.ooredoo.projetfinetude.Services.Impl;

import com.ooredoo.projetfinetude.Entities.LoginResponse;
import com.ooredoo.projetfinetude.Entities.TokenObject;
import com.ooredoo.projetfinetude.Entities.User;
import com.ooredoo.projetfinetude.Repositories.UserRepository;
import com.ooredoo.projetfinetude.Security.Jwt.JwtProvider;
import com.ooredoo.projetfinetude.Services.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationImpl implements Authentication {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public LoginResponse login(User user) {
        try {

            TokenObject tokenObject = new TokenObject();
            Optional<User> utilisateurConnected = repository.findByUsername(user.getUsername());
            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(utilisateurConnected.isPresent()) {
                    tokenObject.setIdUtilisateur(utilisateurConnected.get().getId());
                    tokenObject.setUsername(utilisateurConnected.get().getUsername());
                    tokenObject.setRole(utilisateurConnected.get().getRole().getName().toString());
                    String jwt =jwtProvider.generateJwtToken(tokenObject);
                    return new LoginResponse("200",jwt);
            }
            else
                return new LoginResponse(null,"401");
            //return null;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new LoginResponse(null,"403");
        }

    }
}
