package com.hepsiburada.realtime_recommendation_service.service.impl;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;
import com.hepsiburada.realtime_recommendation_service.entity.ProductView;
import com.hepsiburada.realtime_recommendation_service.repository.ProductViewRepository;
import com.hepsiburada.realtime_recommendation_service.service.BrowsingHistoryService;
import com.hepsiburada.realtime_recommendation_service.util.UserBasedBrowsingHistoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {
    private final ProductViewRepository productViewRepository;

    @Override
    public UserBasedProductResponse findBrowsingHistoryByUserId(String userId) {
        List<ProductView> productViews = productViewRepository.findByUserIdOrderByCreatedDateDesc(userId, PageRequest.of(0, 10));
        List<String> products = productViews.stream()
                .map(ProductView::getProductId)
                .toList();
        return UserBasedProductResponse.builder().userId(userId).products(products).type(UserBasedBrowsingHistoryType.PERSONALIZED).build();
    }

    @Transactional
    @Override
    public void deleteBrowsingHistory(String userId, String productId) {
        productViewRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
