package com.jaytech.coffeeservice.service;

import com.jaytech.coffeeservice.domain.Coffee;
import com.jaytech.coffeeservice.domain.CoffeeOrder;
import com.jaytech.coffeeservice.domain.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public Flux<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Mono<Coffee> getCoffeeById(String id) {
        return coffeeRepository.findById(id);
    }

    public Mono<Coffee> getCoffeeByName(String name) {
        return coffeeRepository.findCoffeeByName(name);
    }

    public Flux<CoffeeOrder> getOrdersForCoffee(String coffeeId) {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(coffee -> new CoffeeOrder(coffeeId, Instant.now()));
    }
}
