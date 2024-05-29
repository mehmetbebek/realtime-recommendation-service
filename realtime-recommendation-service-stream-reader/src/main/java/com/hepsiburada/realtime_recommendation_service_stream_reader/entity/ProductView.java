package com.hepsiburada.realtime_recommendation_service_stream_reader.entity;

import com.hepsiburada.realtime_recommendation_service_stream_reader.domain.ProductViewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product-view")
public class ProductView {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String event;

    private String messageId;

    private String userId;

    private String productId;

    private String source;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public ProductView(ProductViewDto productView) {
        this.event = productView.getEvent();
        this.messageId = productView.getMessageId();
        this.userId = productView.getUserId();
        this.productId = productView.getProperties().getProductId();
        this.source = productView.getContext().getSource().toString();
    }
}