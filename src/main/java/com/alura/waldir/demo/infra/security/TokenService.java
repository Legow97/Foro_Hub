package com.alura.waldir.demo.infra.security;

import com.alura.waldir.demo.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String tokenGenerator(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(48).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);

            String subject = decodedJWT.getSubject();
            if (subject == null) {
                throw new RuntimeException("Subject is null");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println(exception.toString());
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }
}
