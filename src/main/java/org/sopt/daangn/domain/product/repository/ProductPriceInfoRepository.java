package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceInfoRepository extends JpaRepository<ProductPriceInfo, Long> {
    List<ProductPriceInfo> findAllByProduct_Id(Long productId);

    @Query("""
			select ppi
			from ProductPriceInfo ppi
			join fetch ppi.priceInfo
			where ppi.product.id in :productIds
			""")
    List<ProductPriceInfo> findAllWithPriceInfoByProductIds(@Param("productIds") List<Long> productIds);

	@Query("""
			select ppi
			from ProductPriceInfo ppi
			join fetch ppi.priceInfo
			where ppi.product.id = :productId
			""")
	List<ProductPriceInfo> findAllByProductId(@Param(value = "productId") long productId);
}
