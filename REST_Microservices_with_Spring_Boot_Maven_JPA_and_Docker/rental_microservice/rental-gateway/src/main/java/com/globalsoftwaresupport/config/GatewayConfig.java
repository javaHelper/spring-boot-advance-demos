package com.globalsoftwaresupport.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfig {

	// default rest template does not support the PATCH requests
	// we have to include Apache HTTP client
	
	@Bean
	RestTemplate getTemplate() {
		var restTemplate = new RestTemplate();
		var httpClient = HttpClientBuilder.create().build();
		var requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
}
