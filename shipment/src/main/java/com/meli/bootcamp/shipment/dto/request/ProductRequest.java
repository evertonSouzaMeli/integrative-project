package com.meli.bootcamp.shipment.dto.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequest {
    @Min(value = 0, message = "Quantity is must be greater than 0")
    private Long id;

    @Min(value = 0, message = "Quantity is must be greater than 0")
    @Max(value = 10, message = "Quantity is must be less than 0")
    private Integer quantity;
}
