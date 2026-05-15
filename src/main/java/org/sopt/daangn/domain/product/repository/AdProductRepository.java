package org.sopt.daangn.domain.product.repository;

import org.sopt.daangn.domain.product.entity.AdProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdProductRepository extends JpaRepository<AdProduct, Long> {
    List<AdProduct> findTop5ByOrderByIdAsc();
}
