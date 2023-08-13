package com.Trade.Trading;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TradingApplication {



	public static void main(String[] args) {

		SpringApplication.run(TradingApplication.class, args);


	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder().baseUrl("https://api.telegram.org");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
