package com.meli.bootcamp.stock.repositories;

import com.meli.bootcamp.stock.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    boolean existsByProduct_IdAndCart_Id(Long productId, Long cartId);
    CartProduct findByCart_IdAndProduct_Id(Long cartId, Long productId);
    List<CartProduct> findByCartId(Long cartId);
}
