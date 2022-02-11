package com.meli.bootcamp.shipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.bootcamp.shipment.dto.response.ProductResponse;
import com.meli.bootcamp.shipment.enums.CartStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Cart {
    private List<ProductResponse> products = new ArrayList<>();
    private CartStatus cartStatus = CartStatus.ABERTO;
    private BigDecimal total = new BigDecimal("0.0");
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
