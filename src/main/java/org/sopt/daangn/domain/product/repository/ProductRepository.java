package org.sopt.daangn.domain.product.repository;

import java.util.Optional;

import org.sopt.daangn.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	@Query("""
			select p
			from Product p
			left join fetch p.productImages
			where p.id = :productId
			""")
	Optional<Product> findByIdWithImages(@Param(value = "productId") long id);
}
