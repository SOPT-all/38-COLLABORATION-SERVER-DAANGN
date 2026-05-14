package org.sopt.daangn.domain.product.service;

import java.util.ArrayList;
import java.util.List;

import org.sopt.daangn.domain.product.dto.response.CategoryResponse;
import org.sopt.daangn.domain.product.dto.response.ProductListResponse;
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
    private final ProductItemConditionRepository productItemConditionRepository;
    private final ProductTradeTypeRepository productTradeTypeRepository;
    private final ProductPriceInfoRepository productPriceInfoRepository;


	public CategoryResponse getCategories(){
		List<ItemCondition> itemConditions = itemConditionRepository.findAll();
		List<TradeType> tradeTypes = tradeTypeRepository.findAll();
		List<PriceInfo> priceInfos = priceInfoRepository.findAll();

		return CategoryResponse.of(itemConditions, tradeTypes, priceInfos);
	}

    public List<ProductListResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> ProductListResponse.of(product, getTags(product.getId())))
                .toList();
    }

    private List<String> getTags(Long productId) {
        List<String> tags = new ArrayList<>();

        tags.addAll(productItemConditionRepository.findAllByProduct_Id(productId)
                .stream()
                .map(productItemCondition -> productItemCondition.getItemCondition().getName())
                .toList());

        tags.addAll(productTradeTypeRepository.findAllByProduct_Id(productId)
                .stream()
                .map(productTradeType -> productTradeType.getTradeType().getName())
                .toList());

        tags.addAll(productPriceInfoRepository.findAllByProduct_Id(productId)
                .stream()
                .map(productPriceInfo -> productPriceInfo.getPriceInfo().getName())
                .toList());

        return tags;
    }
}
