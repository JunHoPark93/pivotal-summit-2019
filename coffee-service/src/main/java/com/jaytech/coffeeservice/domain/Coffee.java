package com.jaytech.coffeeservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    private String id;
    @NonNull
    private String name;
}
