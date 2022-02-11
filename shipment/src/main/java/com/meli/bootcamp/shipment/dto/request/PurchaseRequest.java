package com.meli.bootcamp.shipment.dto.request;

import lombok.*;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseRequest {
    private String buyerCod;
    @Pattern(regexp = "[0-9]*", message = "Shipping service must be contain only numbers")
    private String shippingService;
}
