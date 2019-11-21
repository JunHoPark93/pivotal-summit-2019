package com.jaytech.coffeeclient.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {
    @Bean
    public WebClient client() {
        return WebClient.create("http://localhost:8080");
    }

    @Bean
    public RSocketRequester requester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 8901).block();
    }
}
