package com.meli.bootcamp.stock.repositories;

import com.meli.bootcamp.stock.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
