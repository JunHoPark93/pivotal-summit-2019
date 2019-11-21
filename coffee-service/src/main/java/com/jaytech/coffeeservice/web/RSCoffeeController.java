package com.jaytech.coffeeservice.web;

import com.jaytech.coffeeservice.domain.Coffee;
import com.jaytech.coffeeservice.domain.CoffeeOrder;
import com.jaytech.coffeeservice.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@AllArgsConstructor
public class RSCoffeeController {
    private final CoffeeService coffeeService;

    @MessageMapping("/coffees")
    public Flux<Coffee> all() {
        return coffeeService.getAllCoffees();
    }

    @MessageMapping("orders.{coffeeName}")
    public Flux<CoffeeOrder> orders(@DestinationVariable String coffeeName) {
        return coffeeService.getCoffeeByName(coffeeName)
                .flatMapMany(coffee -> coffeeService.getOrdersForCoffee(coffee.getId()));
    }
}
