package com.hepsiburada.realtime_recommendation_service.service;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;

public interface BrowsingHistoryService {
    UserBasedProductResponse findBrowsingHistoryByUserId(String userId);

    void deleteBrowsingHistory(String userId, String productId);
}
