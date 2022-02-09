package com.meli.bootcamp.integrativeproject.shipment.controller;

import com.meli.bootcamp.integrativeproject.shipment.entity.Buyer;
import com.meli.bootcamp.integrativeproject.shipment.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/code/{code}")
    public ResponseEntity<Buyer> getByCode(@Valid @PathVariable String code){
        return ResponseEntity.status(HttpStatus.OK).body(buyerService.findByCode(code));
    }

    @PostMapping()
    public ResponseEntity<Buyer> post(@Valid @RequestBody Buyer buyer){
        return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.save(buyer));
    }

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
