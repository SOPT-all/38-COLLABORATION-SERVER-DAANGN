package org.sopt.daangn.domain.product.dto.response;

import java.util.List;

import org.sopt.daangn.domain.product.entity.ItemCondition;
import org.sopt.daangn.domain.product.entity.PriceInfo;
import org.sopt.daangn.domain.product.entity.TradeType;

public record CategoryResponse(
		List<Category> conditions,
		List<Category> tradeTypes,
		List<Category> priceInfos
) {
	public static CategoryResponse of(
			List<ItemCondition> conditions,
			List<TradeType> tradeTypes,
			List<PriceInfo> priceInfos
	) {
		return new CategoryResponse(
				conditions.stream().map(Category::from).toList(),
				tradeTypes.stream().map(Category::from).toList(),
				priceInfos.stream().map(Category::from).toList()
		);
	}

	private record Category(
			String code,
			String name
	) {
		private static Category from(ItemCondition condition) {
			return new Category(condition.getCode(), condition.getName());
		}

		private static Category from(TradeType tradeType) {
			return new Category(tradeType.getCode(), tradeType.getName());
		}

		private static Category from(PriceInfo priceInfo) {
			return new Category(priceInfo.getCode(), priceInfo.getName());
		}
	}
}
