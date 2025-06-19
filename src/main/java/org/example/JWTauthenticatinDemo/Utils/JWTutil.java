package org.example.JWTauthenticatinDemo.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.JWTauthenticatinDemo.Entities.AuthRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTutil {

    private final String SECRET="This is to add somthing like a digital sign";
    private final SecretKey SECRETEKEY= Keys.hmacShaKeyFor(SECRET.getBytes());


   public final long Expiration=1000*60*60;
    public String generateToken(AuthRequest authRequest){

        String token=Jwts.builder().setSubject(authRequest.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+Expiration) )
                .signWith(SECRETEKEY)
                .compact();

        return token;


    }

    public String extractUserName(String token){
        Claims body=extractClaims(token);

        return body.getSubject();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRETEKEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                ;

    }


    public boolean validateToken(String username,String token, UserDetails userDetails){

        Claims extractClaims = extractClaims(token);
        String userName=extractClaims.getSubject();

        if(username.equals(userName)&&!extractClaims.getExpiration().before(new Date())){
            return  true;
        }
        return false;

    }

}
