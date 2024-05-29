package com.hepsiburada.realtime_recommendation_service.dto;

import com.hepsiburada.realtime_recommendation_service.util.UserBasedBrowsingHistoryType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class UserBasedProductResponse {
    private String userId;
    private List<String> products;
    private UserBasedBrowsingHistoryType type;
}
