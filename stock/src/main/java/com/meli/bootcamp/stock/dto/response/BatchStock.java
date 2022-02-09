package com.meli.bootcamp.stock.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BatchStock {
    private Integer batchNumber;
    private Long productId;
    private String productTypeId;
    private LocalDate dueDate;
    private Integer quantity;
}
