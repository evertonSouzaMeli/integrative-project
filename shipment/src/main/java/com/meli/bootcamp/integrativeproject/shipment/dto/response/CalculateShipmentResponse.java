package com.meli.bootcamp.integrativeproject.shipment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.bootcamp.integrativeproject.shipment.entity.Endereco;
import lombok.*;

import java.time.LocalDate;

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
