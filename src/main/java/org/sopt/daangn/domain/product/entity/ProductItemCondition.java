package org.sopt.daangn.domain.product.entity;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_item_condition",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_product_item_condition_product_id_item_condition_id",
						columnNames = {"product_id", "item_condition_id"})
		},
		indexes = {
				@Index(name = "idx_product_item_condition_product_id", columnList = "product_id")
		}
)
public class ProductItemCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_condition_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private ItemCondition itemCondition;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Product product;
}
