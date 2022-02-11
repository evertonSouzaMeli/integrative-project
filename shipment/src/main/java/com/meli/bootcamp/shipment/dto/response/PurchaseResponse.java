package com.meli.bootcamp.shipment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.bootcamp.shipment.entity.Box;
import com.meli.bootcamp.shipment.entity.Buyer;
import com.meli.bootcamp.shipment.exception.NotFoundException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseResponse {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDaCompra;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Buyer buyer;
    private List<Box> boxes = new ArrayList<>();
    private String envio;
    private BigDecimal totalPurchase;

    public String shippingType(String envioRequest){
        switch (Integer.valueOf(envioRequest)) {
            case 40010:
                return "SEDEX s/ contrato";
            case 40045:
                return "SEDEX a Cobrar, sem contrato";
            case 40126:
                return "SEDEX a Cobrar, com contrato";
            case 40215:
                return "SEDEX 10, sem contrato";
            case 40290:
                return "SEDEX Hoje, sem contrato";
            case 40096:
                return "SEDEX com contrato";
            case 40436:
                return "SEDEX com contrato";
            case 40444:
                return "SEDEX com contrato";
            case 40568:
                return "SEDEX com contrato";
            case 40606:
                return "SEDEX com contrato";
            case 41106:
                return "PAC sem contrato";
            case 41068:
                return "PAC com contrato";
            case 81019:
                return "e-SEDEX, com contrato";
            case 81027:
                return "e-SEDEX Prioritário, com conrato";
            case 81035:
                return "e-SEDEX Express, com contrato";
            case 81868:
                return "(Grupo 1) e-SEDEX, com contrato";
            case 81833:
                return "(Grupo 2) e-SEDEX, com contrato";
            case 81850:
                return "(Grupo 3) e-SEDEX, com contrato";
            default:
                throw new NotFoundException(String.format("Shippment code %s is not avaliable", envio));
        }
    }
}
