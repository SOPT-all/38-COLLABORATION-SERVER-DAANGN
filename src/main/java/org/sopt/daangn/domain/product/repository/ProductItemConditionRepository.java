package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductItemCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemConditionRepository extends JpaRepository<ProductItemCondition, Long> {
    List<ProductItemCondition> findAllByProduct_Id(Long productId);
}
