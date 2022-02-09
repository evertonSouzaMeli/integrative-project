package com.meli.bootcamp.stock.repositories;

import com.meli.bootcamp.stock.entity.Product;
import com.meli.bootcamp.stock.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

    List<Product> findAllByName(String name);

    @Query(value = "SELECT SUM(P.QUANTITY) AS " + "QUANTITY_PRODUCT" + ", W.ID AS WAREHOUSE_ID FROM PRODUCTS P JOIN BATCHES B ON (P.BATCH_ID = B.ID) JOIN WAREHOUSES W ON (B.WAREHOUSE_ID = W.ID) WHERE  P.NAME = :name  GROUP BY W.ID", nativeQuery = true)
    List<ProductInWarehouse> findAllByNameInWarehousesIgnoreCase(@Param("name") String name);

    interface ProductInWarehouse{
        Integer getQuantity_product();
        Long  getWarehouse_id();
        String  getProduct_name();
    }
}
