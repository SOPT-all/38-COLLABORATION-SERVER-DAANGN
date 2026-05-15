package org.sopt.daangn.domain.product.dto.response;

import org.sopt.daangn.domain.product.entity.AdProduct;

public record AdProductResponse(
        Long adProductId,
        String title,
        int price,
        String seller,
        String thumbnailUrl
) {
    public static AdProductResponse from(AdProduct adProduct) {
        return new AdProductResponse(
                adProduct.getId(),
                adProduct.getTitle(),
                adProduct.getPrice(),
                adProduct.getSeller(),
                adProduct.getImageUrl()
        );
    }
}