package com.meli.bootcamp.stock.dto.response;

import com.meli.bootcamp.stock.entity.Batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchResponseDTO {
    private Integer batchNumber;

    private List<ProductResponseDTO> products;

    public static BatchResponseDTO toDTO(Batch batch) {
        return BatchResponseDTO.builder()
                .batchNumber(batch.getBatchNumber())
                .products(ProductResponseDTO.entityListToDtoList(batch.getProducts()))
                .build();
    }
}
