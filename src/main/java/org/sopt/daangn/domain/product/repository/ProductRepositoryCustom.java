package org.sopt.daangn.domain.product.repository;

import java.util.List;

import org.sopt.daangn.domain.product.entity.Product;

public interface ProductRepositoryCustom {
    List<Product> findProductsByFilter(
            String conditionCode,
            String tradeTypeCode,
            String priceInfoCode
    );
}