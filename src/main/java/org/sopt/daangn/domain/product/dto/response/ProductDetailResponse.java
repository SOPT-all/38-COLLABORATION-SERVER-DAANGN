package org.sopt.daangn.domain.product.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.sopt.daangn.domain.product.entity.ItemCondition;
import org.sopt.daangn.domain.product.entity.PriceInfo;
import org.sopt.daangn.domain.product.entity.Product;
import org.sopt.daangn.domain.product.entity.ProductImage;
import org.sopt.daangn.domain.product.entity.TradeType;

public record ProductDetailResponse(
		Seller seller,
		String title,
		int price,
		LocalDateTime lastBumpedAt,
		String content,
		String tradeLocation,
		int viewCount,
		boolean isLiked,
		List<String> imageUrls,
		List<String> tags
) {
	public static ProductDetailResponse of(
			Product product,
			List<ItemCondition> itemConditions,
			List<TradeType> tradeTypes,
			List<PriceInfo> priceInfos
	) {
		List<String> tags = new ArrayList<>();

		tags.addAll(itemConditions.stream().map(ItemCondition::getName).toList());
		tags.addAll(tradeTypes.stream().map(TradeType::getName).toList());
		tags.addAll(priceInfos.stream().map(PriceInfo::getName).toList());

		return new ProductDetailResponse(
				Seller.from(product),
				product.getTitle(),
				product.getPrice(),
				product.getLastBumpedAt(),
				product.getContent(),
				product.getTradeLocation(),
				product.getViewCount(),
				product.isLiked(),
				product.getProductImages().stream().map(ProductImage::getImageUrl).toList(),
				tags
		);
	}

	private record Seller(
			String name,
			float mannerTemperature,
			String address
	) {
		private static Seller from(Product product) {
			return new Seller(product.getSellerName(), product.getMannerTemperature(), product.getAddress());
		}
	}
}
