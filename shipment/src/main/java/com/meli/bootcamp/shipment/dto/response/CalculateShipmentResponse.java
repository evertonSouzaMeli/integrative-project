package com.meli.bootcamp.shipment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CalculateShipmentResponse {
    private EnderecoResponse cepOrigem;
    private EnderecoResponse cepDestino;
    private String valor;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String qtdeDias;
}
