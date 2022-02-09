package com.meli.bootcamp.integrativeproject.shipment.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
}
