package com.jimmycasta.pizzeria.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "clase";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    //Crea un JWT para el usuario que inicio sección.
    public String create(String username) {

        return JWT.create()
                .withSubject(username)
                .withIssuer("Nombre_Empresa")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    //Método usado para verificar el Token JWT.
    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    //retorna el usuario que se verifico, se va a usar en un filtro personalizado.
    public String getUserName(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
