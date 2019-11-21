package com.jaytech.coffeeservice.support;

import com.jaytech.coffeeservice.domain.Coffee;
import com.jaytech.coffeeservice.domain.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataLoader {
    private final CoffeeRepository coffeeRepository;

    @PostConstruct
    void loadData() {
        coffeeRepository.deleteAll().thenMany(
                Flux.just("Café Cereza", "Don Pablo", "Espresso Roast", "Juan Valdez", "Kaldi's Coffee", "Kona")
                        .map(Coffee::new)
                        .flatMap(coffeeRepository::save))
                .thenMany(coffeeRepository.findAll())
                .subscribe(System.out::println);
    }
}
