package com.meli.bootcamp.shipment.controller;

import com.meli.bootcamp.shipment.dto.request.ProductRequest;
import com.meli.bootcamp.shipment.dto.request.PurchaseRequest;
import com.meli.bootcamp.shipment.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

    @PostMapping("/add-products")
    public ResponseEntity<Object> purchaseOrder(@Valid @RequestParam(name = "buyerCode") String buyerCode, @RequestBody List<ProductRequest> productRequestList){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.addProducts(buyerCode, productRequestList));
    }

    @PostMapping("/finalize-purchase")
    public ResponseEntity<Object> purchaseOrder(@Valid @RequestBody PurchaseRequest purchaseRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.makePurchase(purchaseRequest));
    }
}
