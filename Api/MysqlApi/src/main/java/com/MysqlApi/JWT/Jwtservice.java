package com.MysqlApi.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Jwtservice {
    private final String SECRET_STRING = "feiuhfabnfoiahv;kfrn;seaiofha8iifhnwk;lfbhdsiuoh";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    private final long EXPIRATION_TIME =  1000*3600*24;//1000*3600*24;

    public String createJWT(String username){
        Map<String,String> claims = new HashMap<>();
        claims.put("username",username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SECRET_KEY, io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean checkJWT(String token, String username){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String tokenUsername = claims.get("username").toString();
            return tokenUsername.equals(username);
        }catch (Exception e){
            return false;
        }
    }
}
