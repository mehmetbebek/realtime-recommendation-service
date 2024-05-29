package com.hepsiburada.realtime_recommendation_service.service;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;

public interface BestSellerProductsService {

    UserBasedProductResponse findBestSellerProductsByUserId(String userId);
}
