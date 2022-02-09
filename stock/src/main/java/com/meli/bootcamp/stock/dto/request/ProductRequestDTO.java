package com.meli.bootcamp.stock.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.meli.bootcamp.stock.entity.Product;
import com.meli.bootcamp.stock.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String name;

    private Double currentTemperature;

    private Double minimalTemperature;

    private Integer quantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dueDate;

    private Category category;

    private Double price;

    public static Product toEntity(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.getName())
                .currentTemperature(productRequestDTO.getCurrentTemperature())
                .minimalTemperature(productRequestDTO.getMinimalTemperature())
                .quantity(productRequestDTO.getQuantity())
                .dueDate(productRequestDTO.getDueDate())
                .category(productRequestDTO.getCategory())
                .price(productRequestDTO.getPrice())
                .build();
    }
}
