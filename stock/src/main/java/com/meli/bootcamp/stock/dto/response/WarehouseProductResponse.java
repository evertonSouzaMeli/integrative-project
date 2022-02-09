package com.meli.bootcamp.stock.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WarehouseProductResponse {

    private String productId;

    @JsonProperty(value = "warehouses")
    private List<WarehouseProductDTO> productsDTO;
}