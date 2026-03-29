package com.example;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

@Configuration
public class WebConfig {

    @Bean
    public WebClient webClient() throws Exception {

        // Load client cert
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(
            new ClassPathResource("client.p12").getInputStream(),
            "password".toCharArray()
        );

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm()
        );
        kmf.init(keyStore, "password".toCharArray());

        // Truststore
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(
            new ClassPathResource("truststore.p12").getInputStream(),
            "password".toCharArray()
        );

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm()
        );
        tmf.init(trustStore);

        SslContext sslContext = SslContextBuilder
                .forClient()
                .keyManager(kmf)
                .trustManager(tmf)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}