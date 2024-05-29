package com.hepsiburada.realtime_recommendation_service.service.impl;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;
import com.hepsiburada.realtime_recommendation_service.repository.ProductRepository;
import com.hepsiburada.realtime_recommendation_service.repository.ProductViewRepository;
import com.hepsiburada.realtime_recommendation_service.service.BestSellerProductsService;
import com.hepsiburada.realtime_recommendation_service.util.UserBasedBrowsingHistoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BestSellerProductsServiceImpl implements BestSellerProductsService {

    private final ProductViewRepository productViewRepository;

    private final ProductRepository productRepository;

    @Override
    public UserBasedProductResponse findBestSellerProductsByUserId(String userId) {
        boolean existsByUserId = productViewRepository.existsByUserId(userId);
        List<Object[]> topTenProductsBoughtLastMonth;
        UserBasedBrowsingHistoryType type;

        if (!existsByUserId) {
            topTenProductsBoughtLastMonth = productRepository.findTopTenProductsBoughtLastMonth();
            type = UserBasedBrowsingHistoryType.NON_PERSONALIZED;
        } else {
            topTenProductsBoughtLastMonth = productRepository.findTopTenProductsBoughtLastMonth(userId);
            type = UserBasedBrowsingHistoryType.PERSONALIZED;
        }

        List<String> products = topTenProductsBoughtLastMonth.stream()
                .map(item -> String.valueOf(item[0]))
                .toList();

        if (products.size() < 5) {
            products = new ArrayList<>();
        }

        return UserBasedProductResponse.builder().userId(userId).products(products).type(type).build();
    }
}
