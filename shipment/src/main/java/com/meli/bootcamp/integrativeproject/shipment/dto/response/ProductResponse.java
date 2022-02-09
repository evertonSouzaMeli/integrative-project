package com.meli.bootcamp.integrativeproject.shipment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {
    private String name;
    private Integer quantity;
    private LocalDate dueDate;
    private Float price;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float altura = 1.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float diametro = 1.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float largura = 1.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float comprimento = 1.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float peso = 1.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private WarehouseResponse warehouse;
}
