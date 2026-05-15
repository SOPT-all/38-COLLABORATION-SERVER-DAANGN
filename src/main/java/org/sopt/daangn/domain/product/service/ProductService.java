package org.sopt.daangn.domain.product.service;

import java.util.List;

import org.sopt.daangn.domain.product.dto.response.AdProductResponse;
import org.sopt.daangn.domain.product.dto.response.CategoryResponse;
import org.sopt.daangn.domain.product.entity.AdProduct;
import org.sopt.daangn.domain.product.entity.ItemCondition;
import org.sopt.daangn.domain.product.entity.PriceInfo;
import org.sopt.daangn.domain.product.entity.TradeType;
import org.sopt.daangn.domain.product.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ItemConditionRepository itemConditionRepository;
	private final TradeTypeRepository tradeTypeRepository;
	private final PriceInfoRepository priceInfoRepository;
    private final AdProductRepository adProductRepository;


	public CategoryResponse getCategories(){
		List<ItemCondition> itemConditions = itemConditionRepository.findAll();
		List<TradeType> tradeTypes = tradeTypeRepository.findAll();
		List<PriceInfo> priceInfos = priceInfoRepository.findAll();

		return CategoryResponse.of(itemConditions, tradeTypes, priceInfos);
	}
    public List<AdProductResponse> getAdProducts() {
        List<AdProduct> adProducts = adProductRepository.findTop5ByOrderByIdAsc();

        return adProducts.stream()
                .map(AdProductResponse::from)
                .toList();
    }
}
