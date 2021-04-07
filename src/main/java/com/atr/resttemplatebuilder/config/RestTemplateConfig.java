package com.atr.resttemplatebuilder.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplateHandler;

/**
 * 
 * @author Ahmed Al Salih
 * 
 *
 */
@Configuration
public class RestTemplateConfig {
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		UriTemplateHandler uriTemplateHandler = new RootUriTemplateHandler("https://document-URL");
		return builder
				.uriTemplateHandler(uriTemplateHandler)
				.build();
	}
	

}