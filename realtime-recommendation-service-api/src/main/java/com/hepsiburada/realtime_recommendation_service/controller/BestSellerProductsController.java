package com.hepsiburada.realtime_recommendation_service.controller;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;
import com.hepsiburada.realtime_recommendation_service.service.BestSellerProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/best-seller-products")
@RequiredArgsConstructor
public class BestSellerProductsController {

    private final BestSellerProductsService bestSellerProductsService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserBasedProductResponse> getBestSellerProducts(@PathVariable String userId) {
        return ResponseEntity.ok(bestSellerProductsService.findBestSellerProductsByUserId(userId));
    }

}
