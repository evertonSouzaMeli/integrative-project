package com.meli.bootcamp.stock.repositories;

import com.meli.bootcamp.stock.entity.InboundOrder;

import com.meli.bootcamp.stock.entity.WarehouseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    @Query(value = "SELECT ws FROM WarehouseSection ws WHERE ws.warehouse.id = :warehouseId AND ws.section.id = :sectionId")
    WarehouseSection findWarehouseSectionByWarehouseId(@Param("warehouseId") Long warehouseId, @Param("sectionId") Long sectionId);
}
