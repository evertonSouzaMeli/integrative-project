package com.meli.bootcamp.stock.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderRequestDTO {

    private Long sectionId;

    private Long warehouseId;

    private Long sellerId;

    private BatchRequestDTO batchStock;
}
