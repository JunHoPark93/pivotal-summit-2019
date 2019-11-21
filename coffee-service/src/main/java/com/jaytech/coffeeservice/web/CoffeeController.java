package com.jaytech.coffeeservice.web;

import com.jaytech.coffeeservice.domain.Coffee;
import com.jaytech.coffeeservice.domain.CoffeeOrder;
import com.jaytech.coffeeservice.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffees")
@AllArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;

    @GetMapping
    Flux<Coffee> all() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{id}")
    Mono<Coffee> byId(@PathVariable String id) {
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping(value = "/{id}/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<CoffeeOrder> orders(@PathVariable String id) {
        return coffeeService.getOrdersForCoffee(id);
    }
}
