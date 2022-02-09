package com.meli.bootcamp.stock.repositories;

import com.meli.bootcamp.stock.entity.Warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}
