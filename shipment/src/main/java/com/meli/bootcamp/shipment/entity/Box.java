package com.meli.bootcamp.shipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.bootcamp.shipment.dto.response.EnderecoResponse;
import com.meli.bootcamp.shipment.dto.response.ProductResponse;
import com.meli.bootcamp.shipment.dto.response.WarehouseResponse;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Box {
    private List<ProductResponse> products = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float altura = 0.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float largura = 0.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float comprimento = 0.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float diametro = 0.0F;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Float peso = 0.0F;
    private BigDecimal valorFrete = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate previsaoEntrega;
    private WarehouseResponse remetente;


    public static List<Box> createBox(List<ProductResponse> productResponseList) {
        List<String> ceps = new ArrayList<>();
        List<Box> boxes = new ArrayList<>();

        productResponseList.forEach(product -> ceps.add(product.getWarehouse().getCep()));

        Set<String> setCep = new HashSet<>(ceps);
        ceps.clear();
        ceps.addAll(setCep);

        ceps.forEach(cep -> {
            Box box = new Box();
            EnderecoResponse enderecoResponse = new EnderecoResponse();
            productResponseList.forEach(product -> {
                        if (product.getWarehouse().getCep().equals(cep)) {
                            enderecoResponse.setCep(cep);

                            box.setAltura(box.getAltura() + product.getAltura());
                            box.setLargura(box.getLargura() + product.getLargura());
                            box.setComprimento(box.getComprimento() + product.getComprimento());
                            box.setDiametro(box.getDiametro() + product.getDiametro());
                            box.setPeso(box.getPeso() + product.getPeso());
                            box.setRemetente(product.getWarehouse());

                            box.getProducts().add(product);
                        }
                    });
                    boxes.add(box);
            });
        return boxes;
    }
}
