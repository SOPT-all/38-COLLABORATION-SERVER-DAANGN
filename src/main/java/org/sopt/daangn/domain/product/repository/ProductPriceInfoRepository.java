package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.ProductPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPriceInfoRepository extends JpaRepository<ProductPriceInfo, Long> {
    List<ProductPriceInfo> findAllByProduct_Id(Long productId);
}
