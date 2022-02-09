package com.meli.bootcamp.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PurchaseOrderProductRequest {
    private Long productId;
    private Integer quantity;
}