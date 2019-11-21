package com.jaytech.coffeeclient.web;

import com.jaytech.coffeeclient.domain.Coffee;
import com.jaytech.coffeeclient.domain.CoffeeOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class CoffeeClient {
    private final WebClient webClient;

    @PostConstruct
    public void runIt() {
        webClient.get()
                .uri("/coffees")
                .retrieve()
                .bodyToFlux(Coffee.class)
                .filter(coffee -> coffee.getName().equalsIgnoreCase("kona"))
                .flatMap(coffee ->
                        webClient.get()
                                .uri("/coffees/{id}/orders", coffee.getId())
                                .retrieve()
                                .bodyToFlux(CoffeeOrder.class))
                .subscribe(System.out::println);
    }
}
