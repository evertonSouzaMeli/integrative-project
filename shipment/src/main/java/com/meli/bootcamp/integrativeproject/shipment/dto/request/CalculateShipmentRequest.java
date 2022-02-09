package com.meli.bootcamp.integrativeproject.shipment.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CalculateShipmentRequest {
    private String servico;
    private String cepOrigem;
    private String cepDestino;
    private String formato; //1 - caixa /pacote
    private float comprimento;
    private float largura;
    private float altura;
    private float diametro;
    private float peso;
}
