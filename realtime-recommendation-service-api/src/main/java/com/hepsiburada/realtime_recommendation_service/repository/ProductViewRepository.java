package com.hepsiburada.realtime_recommendation_service.repository;

import com.hepsiburada.realtime_recommendation_service.entity.ProductView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductViewRepository extends JpaRepository<ProductView, UUID> {

    List<ProductView> findByUserIdOrderByCreatedDateDesc(String userId, Pageable pageable);

    void deleteByUserIdAndProductId(String userId, String productId);

    boolean existsByUserId(String userId);
}
