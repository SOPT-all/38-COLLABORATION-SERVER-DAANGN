package org.sopt.daangn.domain.product.dto.response;

import org.sopt.daangn.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public record ProductListResponse (
    Long productId,
    String title,
    String tradeLocation,
    LocalDateTime lastBumpedAt,
    int price,
    String thumbnailUrl,
    int likeCount,
    List<String> tags,
    boolean isLiked
) {
    public static ProductListResponse of(
            Product product,
            List<String> tags
    ){
        return new ProductListResponse(
                product.getId(),
                product.getTitle(),
                product.getTradeLocation(),
                product.getLastBumpedAt(),
                product.getPrice(),
                product.getThumbnailUrl(),
                product.getLikeCount(),
                tags,
                product.isLiked()
        );
    }
}

