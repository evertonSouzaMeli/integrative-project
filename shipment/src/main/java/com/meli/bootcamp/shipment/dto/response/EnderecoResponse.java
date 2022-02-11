package com.meli.bootcamp.shipment.dto.response;

import com.meli.bootcamp.shipment.entity.Endereco;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EnderecoResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;

    public static Endereco toEntity(EnderecoResponse enderecoResponse){
        Endereco endereco = Endereco.builder()
                                    .cep(enderecoResponse.getCep())
                                    .logradouro(enderecoResponse.getLogradouro())
                                    .bairro(enderecoResponse.getBairro())
                                    .uf(enderecoResponse.getUf())
                                    .build();
        return endereco;
    }
}
