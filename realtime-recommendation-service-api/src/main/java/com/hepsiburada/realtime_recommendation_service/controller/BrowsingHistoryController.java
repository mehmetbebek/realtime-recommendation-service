package com.hepsiburada.realtime_recommendation_service.controller;

import com.hepsiburada.realtime_recommendation_service.dto.UserBasedProductResponse;
import com.hepsiburada.realtime_recommendation_service.service.BrowsingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/browsing-history")
@RequiredArgsConstructor
public class BrowsingHistoryController {

    private final BrowsingHistoryService browsingHistoryService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserBasedProductResponse> getBrowsingHistory(@PathVariable String userId) {
        return ResponseEntity.ok(browsingHistoryService.findBrowsingHistoryByUserId(userId));
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> deleteBrowsingHistory(@PathVariable String userId, @PathVariable String productId) {
        browsingHistoryService.deleteBrowsingHistory(userId, productId);
        return ResponseEntity.accepted().build();
    }

}
