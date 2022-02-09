package com.meli.bootcamp.integrativeproject.shipment.service;

import com.meli.bootcamp.integrativeproject.shipment.dto.request.ProductRequest;
import com.meli.bootcamp.integrativeproject.shipment.dto.request.PurchaseRequest;
import com.meli.bootcamp.integrativeproject.shipment.dto.response.*;
import com.meli.bootcamp.integrativeproject.shipment.entity.Box;
import com.meli.bootcamp.integrativeproject.shipment.entity.Buyer;
import com.meli.bootcamp.integrativeproject.shipment.entity.Cart;
import com.meli.bootcamp.integrativeproject.shipment.enums.CartStatus;
import com.meli.bootcamp.integrativeproject.shipment.exception.BusinessException;
import com.meli.bootcamp.integrativeproject.shipment.exception.NotFoundException;
import com.meli.bootcamp.integrativeproject.shipment.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private BuyerRepository buyerRepository;

    public PurchaseResponse makePurchase(PurchaseRequest purchaseRequest) {

        if(purchaseRequest.getShippingService() == null)
            throw new BusinessException("Shipping service is null");
        if(purchaseRequest.getBuyerCod() == null)
            throw new BusinessException("Buyer code is null");

        PurchaseResponse purchaseResponse = new PurchaseResponse();
        Buyer buyer = buyerRepository.findByCode(purchaseRequest.getBuyerCod()).get();

        List<Box> boxes = Box.createBox(buyer.getCart().getProducts());
        List<Box> updateBox = new ArrayList<>();

        if(buyer.getCart().getProducts().isEmpty())
            throw new BusinessException("cart is empty! try adding products before finishing");


        boxes.forEach(box -> {
            updateBox.add(ExternalAPIHelper.calculateShipment(box, purchaseRequest, buyer));
        });

        purchaseResponse.setTotalPurchase(BigDecimal.valueOf(updateBox.stream()
                .mapToDouble(box -> box.getValorFrete().doubleValue() + box.getProducts().stream()
                        .mapToDouble(product -> product.getPrice() * product.getQuantity()).sum()).sum()).setScale(2, RoundingMode.HALF_EVEN));

        purchaseResponse.setBoxes(updateBox);
        purchaseResponse.setBuyer(buyer);
        purchaseResponse.setDataDaCompra(LocalDateTime.now());
        purchaseResponse.setEnvio(purchaseResponse.shippingType(purchaseRequest.getShippingService()));
        buyer.setCart(new Cart());
        buyerRepository.save(buyer);
        return purchaseResponse;
    }


    public Cart addProducts(String buyerCode, List<ProductRequest> productRequestList) {
        productRequestList.forEach(product -> productsRequestValidation(product));
        Buyer buyer = buyerRepository.findByCode(buyerCode).orElseThrow(() -> new NotFoundException("Buyer not find"));
        Cart cart = buyer.findOpenCart(buyer);

        Double total = productRequestList.stream().mapToDouble(productRequest -> {
            ProductResponse productResponse = new ProductResponse();
            try {
                productResponse = ExternalAPIHelper.searchProduct(productRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cart.getProducts().add(productResponse);
            return productResponse.getPrice() * productResponse.getQuantity();
        }).sum();

        cart.setTotal(new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN));
        buyer.setCart(cart);
        buyerRepository.save(buyer);
        return buyer.getCart();
    }

    public void productsRequestValidation(ProductRequest productRequest){
        if(productRequest.getQuantity() <= 0)
            throw new BusinessException("Quantity can't be less than 0");
    }
}