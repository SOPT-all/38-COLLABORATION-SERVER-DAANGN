package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductTradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductTradeTypeRepository extends JpaRepository<ProductTradeType, Long> {
    List<ProductTradeType> findAllByProduct_Id(Long productId);

    @Query("""
			select ptt
			from ProductTradeType ptt
			join fetch ptt.tradeType
			where ptt.product.id in :productIds
			""")
    List<ProductTradeType> findAllWithTradeTypeByProductIds(@Param("productIds") List<Long> productIds);

	@Query("""
			select ptt
			from ProductTradeType ptt
			join fetch ptt.tradeType
			where ptt.product.id = :productId
			""")
	List<ProductTradeType> findAllByProductId(@Param(value = "productId") long productId);
}
