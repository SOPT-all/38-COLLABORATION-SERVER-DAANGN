package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductItemCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductItemConditionRepository extends JpaRepository<ProductItemCondition, Long> {
    List<ProductItemCondition> findAllByProduct_Id(Long productId);

    @Query("""
			select pic
			from ProductItemCondition pic
			join fetch pic.itemCondition
			where pic.product.id in :productIds
			""")
    List<ProductItemCondition> findAllWithItemConditionByProductIds(@Param("productIds") List<Long> productIds);
}
