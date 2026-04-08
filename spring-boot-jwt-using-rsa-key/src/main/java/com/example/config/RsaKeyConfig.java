package com.example.config;

import com.example.util.PemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class RsaKeyConfig {

    @Value("classpath:public.pem")
    private Resource publicKeyResource;

    @Value("classpath:private.pem")
    private Resource privateKeyResource;

    @Bean
    public RSAPublicKey publicKey() throws Exception {
        return PemUtils.loadPublicKey(publicKeyResource);
    }

    @Bean
    public RSAPrivateKey privateKey() throws Exception {
        return PemUtils.loadPrivateKey(privateKeyResource);
    }
}