package com.meli.bootcamp.shipment.service;

import com.meli.bootcamp.shipment.dto.response.EnderecoResponse;
import com.meli.bootcamp.shipment.entity.Buyer;
import com.meli.bootcamp.shipment.entity.Cart;
import com.meli.bootcamp.shipment.exception.NotFoundException;
import com.meli.bootcamp.shipment.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer findByCode(String cpf){
        return buyerRepository.findByCode(cpf).orElseThrow(() -> new NotFoundException("Buyer not Found"));
    }


    public Buyer save(Buyer buyer){
        return buyerRepository.save(Buyer.builder()
                                         .name(buyer.getName())
                                         .code(generateCode())
                                         .endereco(EnderecoResponse.toEntity(ExternalAPIHelper.searchAddress(buyer.getEndereco().getCep())))
                                         .cart(new Cart())
                                         .build());
    }

    public String generateCode(){
        Integer min = 100000;
        Integer max = 999999;
        Integer code = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return "COD"+code;
    }
}
