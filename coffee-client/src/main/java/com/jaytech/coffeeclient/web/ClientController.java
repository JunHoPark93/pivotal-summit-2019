package com.jaytech.coffeeclient.web;

import com.jaytech.coffeeclient.domain.Coffee;
import com.jaytech.coffeeclient.domain.CoffeeOrder;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class ClientController {
    private final RSocketRequester requester;

    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return requester.route("/coffees").retrieveFlux(Coffee.class);
    }

    @GetMapping(value = "/orders/{coffeeName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CoffeeOrder> orders(@PathVariable String coffeeName) {
        return requester.route("orders.".concat(coffeeName)).retrieveFlux(CoffeeOrder.class);
    }
}
