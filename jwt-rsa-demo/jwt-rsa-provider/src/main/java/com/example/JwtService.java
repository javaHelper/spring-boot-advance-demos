package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private KeyUtil keyUtil;

    public String generateToken(String username) throws Exception {
        PrivateKey privateKey = keyUtil.getPrivateKey();

        Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);

        return JWT.create()
                .withSubject(username)
                .withIssuer("producer-app")
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);
    }
}