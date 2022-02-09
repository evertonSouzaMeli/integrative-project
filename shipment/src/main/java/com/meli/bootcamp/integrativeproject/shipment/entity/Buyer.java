package com.meli.bootcamp.integrativeproject.shipment.entity;


import com.meli.bootcamp.integrativeproject.shipment.enums.CartStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document(collection = "buyer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Buyer {
    @Id
    private String id;
    @Size(min = 5, max = 30, message = "The name must contain between 5 or 30 characters")
    private String name;
    @Indexed(unique = true, background = true)
    private String code;
    private Endereco endereco;
    private Cart cart;

    public Cart findOpenCart(Buyer buyer){
        if(buyer.getCart().getCartStatus().equals(CartStatus.FECHADO))
           return new Cart();
        return cart;
    }
}
