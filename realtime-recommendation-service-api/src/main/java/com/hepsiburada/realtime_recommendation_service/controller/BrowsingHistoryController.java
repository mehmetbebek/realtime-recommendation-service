package com.hepsiburada.realtime_recommendation_service.controller;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedBrowsingHistoryResponse;
import com.hepsiburada.realtime_recommendation_service.util.UserBasedBrowsingHistoryType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/browsing-history")
public class BrowsingHistoryController {

    @GetMapping( "/{userId}")
    public ResponseEntity<UserBasedBrowsingHistoryResponse> getBrowsingHistory(@PathVariable String userId) {
        UserBasedBrowsingHistoryResponse response = new UserBasedBrowsingHistoryResponse();

        response.setUserId(UUID.randomUUID().toString());
        response.setProducts(new ArrayList<>());
        response.setType(UserBasedBrowsingHistoryType.PERSONALIZED);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> deleteBrowsingHistory(@PathVariable String userId, @PathVariable String productId) {

        return ResponseEntity.accepted().build();
    }

}
