package com.meli.bootcamp.shipment.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WarehouseRequest {
    private Long id;
    private String name;
    private String cep;
    private String numero;
}
