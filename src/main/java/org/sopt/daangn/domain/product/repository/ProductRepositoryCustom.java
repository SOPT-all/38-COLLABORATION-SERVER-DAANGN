package org.sopt.daangn.domain.product.repository;

import java.util.List;

import org.sopt.daangn.domain.product.entity.Product;

public interface ProductRepositoryCustom {
    List<Product> findProductsByFilter(
            Integer minPrice,
            Integer maxPrice,
            String distanceCode,
            String conditionCode,
            String tradeTypeCode,
            String priceInfoCode
    );
}