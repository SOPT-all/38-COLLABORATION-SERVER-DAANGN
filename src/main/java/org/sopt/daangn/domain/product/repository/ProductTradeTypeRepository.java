package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductTradeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTradeTypeRepository extends JpaRepository<ProductTradeType, Long> {
    List<ProductTradeType> findAllByProduct_Id(Long productId);
}
