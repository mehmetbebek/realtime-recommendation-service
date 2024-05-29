package com.hepsiburada.realtime_recommendation_service_stream_reader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsiburada.realtime_recommendation_service_stream_reader.domain.ProductViewDto;
import com.hepsiburada.realtime_recommendation_service_stream_reader.entity.ProductView;
import com.hepsiburada.realtime_recommendation_service_stream_reader.repository.ProductViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HepsiburadaViewsConsumer {

    private final ProductViewRepository productViewRepository;

    @KafkaListener(topics = "views", groupId = "hepsiburada-views-group")
    public void consumeViews(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ProductViewDto productView = objectMapper.readValue(message, ProductViewDto.class);

        productViewRepository.save(new ProductView(productView));
    }
}
