package com.meli.bootcamp.stock.controller;

import com.meli.bootcamp.stock.dto.request.PurchaseOrderRequest;
import com.meli.bootcamp.stock.dto.response.CartProductResponseDTO;
import com.meli.bootcamp.stock.entity.CartProduct;
import com.meli.bootcamp.stock.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fresh-products/orders")
public class PurchaseOrderController {

    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<List<CartProductResponseDTO>> findByCartId(@PathVariable Long idOrder) {
        List<CartProduct> cartProducts = purchaseOrderService.findByCartId(idOrder);

        return ResponseEntity.ok().body(CartProductResponseDTO.entityListToResponseDtoList(cartProducts));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody PurchaseOrderRequest request){
        return ResponseEntity.created(null).body(purchaseOrderService.save(request));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestParam(name = "idOrder") Long id, @RequestBody PurchaseOrderRequest request) {
        return ResponseEntity.ok().body(purchaseOrderService.update(request, id));
    }
}
