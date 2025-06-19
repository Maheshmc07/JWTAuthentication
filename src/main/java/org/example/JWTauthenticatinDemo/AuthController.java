package org.example.JWTauthenticatinDemo;

import org.example.JWTauthenticatinDemo.Entities.AuthRequest;
import org.example.JWTauthenticatinDemo.Utils.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthController {
    @Autowired
    public JWTutil JWTutil;

@Autowired
private AuthenticationManager manager;
    @PostMapping("/auth")
    public String AuthRequest(@RequestBody AuthRequest authRequest){


        String Token="hello";
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            ));
            Token = JWTutil.generateToken(authRequest);

        }
        catch (Exception e){
            throw new RuntimeException("Invalid username or password");

        }

       return Token;
    }
}

