package com.meli.bootcamp.stock.dto.response;


import com.meli.bootcamp.stock.entity.Product;
import com.meli.bootcamp.stock.entity.Warehouse;
import com.meli.bootcamp.stock.enums.Category;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponseExternalAPI {
    private Long id;
    private String name;
    private Double currentTemperature;
    private Double minimalTemperature;
    private Integer quantity;
    private LocalDate dueDate;
    private Double price;
    private Category category;
    private Warehouse warehouse;

    public static ProductResponseExternalAPI toResponse(Product product){
        ProductResponseExternalAPI productResponseExternalAPI = ProductResponseExternalAPI.builder()
                .id(product.getId())
                .name(product.getName())
                .currentTemperature(product.getCurrentTemperature())
                .minimalTemperature(product.getMinimalTemperature())
                .quantity(product.getQuantity())
                .dueDate(product.getDueDate())
                .price(product.getPrice())
                .category(product.getCategory())
                .warehouse(product.getBatch().getWarehouse())
                .build();
        return productResponseExternalAPI;
    }
}
