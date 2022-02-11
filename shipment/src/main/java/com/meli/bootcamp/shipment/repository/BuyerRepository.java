package com.meli.bootcamp.shipment.repository;

import com.meli.bootcamp.shipment.entity.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findByCode(String code);
}
