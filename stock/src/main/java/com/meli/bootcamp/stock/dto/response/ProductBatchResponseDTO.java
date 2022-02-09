package com.meli.bootcamp.stock.dto.response;

import com.meli.bootcamp.stock.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBatchResponseDTO {

    private String productName;

    private BatchProduct batch;

    public static ProductBatchResponseDTO toDTO(Product product) {
        BatchProduct batchProduct = BatchProduct.builder()
                .batchId(product.getBatch().getId())
                .batchNumber(product.getBatch().getBatchNumber())
                .productQuantity(product.getQuantity())
                .productDueDate(product.getDueDate())
                .build();

        return ProductBatchResponseDTO.builder()
                .productName(product.getName())
                .batch(batchProduct)
                .build();
    }

    public static List<ProductBatchResponseDTO> entityListToDtoList(List<Product> products) {
        List<ProductBatchResponseDTO> productBatchResponseDTOS = new ArrayList<>();
        for (Product product : products) {
            if (product.getDueDate().compareTo(LocalDate.now().plusWeeks(3)) > 0) {
                productBatchResponseDTOS.add(toDTO(product));
            }
        }

        return productBatchResponseDTOS;
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class BatchProduct {
    private Long batchId;

    private Integer batchNumber;

    private Integer productQuantity;

    private LocalDate productDueDate;
}
