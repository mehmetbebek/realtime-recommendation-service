package com.hepsiburada.realtime_recommendation_service_stream_reader.repository;

import com.hepsiburada.realtime_recommendation_service_stream_reader.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductViewRepository extends JpaRepository<ProductView, UUID> {
}
