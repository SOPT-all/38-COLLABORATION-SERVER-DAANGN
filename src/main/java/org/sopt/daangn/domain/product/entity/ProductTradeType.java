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
@Table(name = "product_trade_type",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_product_trade_type_product_id_trade_type_id",
						columnNames = {"product_id", "trade_type_id"})
		},
		indexes = {
				@Index(name = "idx_product_trade_type_product_id", columnList = "product_id")
		}
)
public class ProductTradeType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trade_type_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private TradeType tradeType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Product product;
}
