package org.sopt.daangn.domain.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sopt.daangn.domain.product.dto.response.AdProductResponse;
import org.sopt.daangn.domain.product.dto.response.CategoryResponse;
import org.sopt.daangn.domain.product.dto.response.ProductDetailResponse;
import org.sopt.daangn.domain.product.dto.response.ProductListResponse;
import org.sopt.daangn.domain.product.entity.AdProduct;
import org.sopt.daangn.domain.product.entity.ItemCondition;
import org.sopt.daangn.domain.product.entity.PriceInfo;
import org.sopt.daangn.domain.product.entity.Product;
import org.sopt.daangn.domain.product.entity.ProductItemCondition;
import org.sopt.daangn.domain.product.entity.ProductPriceInfo;
import org.sopt.daangn.domain.product.entity.ProductTradeType;
import org.sopt.daangn.domain.product.entity.TradeType;
import org.sopt.daangn.domain.product.exception.ProductNotFoundException;
import org.sopt.daangn.domain.product.repository.AdProductRepository;
import org.sopt.daangn.domain.product.repository.ItemConditionRepository;
import org.sopt.daangn.domain.product.repository.PriceInfoRepository;
import org.sopt.daangn.domain.product.repository.ProductItemConditionRepository;
import org.sopt.daangn.domain.product.repository.ProductPriceInfoRepository;
import org.sopt.daangn.domain.product.repository.ProductRepository;
import org.sopt.daangn.domain.product.repository.ProductTradeTypeRepository;
import org.sopt.daangn.domain.product.repository.TradeTypeRepository;
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
    private final ProductItemConditionRepository productItemConditionRepository;
    private final ProductTradeTypeRepository productTradeTypeRepository;
    private final ProductPriceInfoRepository productPriceInfoRepository;

    public CategoryResponse getCategories() {
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

    public List<ProductListResponse> getProducts(
            Integer minPrice,
            Integer maxPrice,
            String distanceCode,
            String conditionCode,
            String tradeTypeCode,
            String priceInfoCode
    ) {
        List<Product> products = productRepository.findProductsByFilter(
                minPrice,
                maxPrice,
                distanceCode,
                conditionCode,
                tradeTypeCode,
                priceInfoCode
        );

        List<Long> productIds = products.stream()
                .map(Product::getId)
                .toList();

        Map<Long, List<String>> tagsByProductId = getTagsByProductId(productIds);

        return products.stream()
                .map(product -> ProductListResponse.of(
                        product,
                        tagsByProductId.getOrDefault(product.getId(), List.of())
                ))
                .toList();
    }

    public ProductDetailResponse getProductDetail(long id) {
        Product product = productRepository.findByIdWithImages(id).orElseThrow(ProductNotFoundException::new);

        List<ItemCondition> itemConditions = productItemConditionRepository.findAllByProductId(id)
                .stream()
                .map(ProductItemCondition::getItemCondition)
                .toList();
        List<TradeType> tradeTypes = productTradeTypeRepository.findAllByProductId(id)
                .stream()
                .map(ProductTradeType::getTradeType)
                .toList();
        List<PriceInfo> priceInfos = productPriceInfoRepository.findAllByProductId(id)
                .stream()
                .map(ProductPriceInfo::getPriceInfo)
                .toList();
        return ProductDetailResponse.of(product, itemConditions, tradeTypes, priceInfos);
    }

    private Map<Long, List<String>> getTagsByProductId(List<Long> productIds) {
        Map<Long, List<String>> tagsByProductId = new HashMap<>();

        if (productIds.isEmpty()) {
            return tagsByProductId;
        }

        List<ProductItemCondition> productItemConditions =
                productItemConditionRepository.findAllWithItemConditionByProductIds(productIds);

        for (ProductItemCondition productItemCondition : productItemConditions) {
            addTag(
                    tagsByProductId,
                    productItemCondition.getProduct().getId(),
                    productItemCondition.getItemCondition().getName()
            );
        }

        List<ProductTradeType> productTradeTypes =
                productTradeTypeRepository.findAllWithTradeTypeByProductIds(productIds);

        for (ProductTradeType productTradeType : productTradeTypes) {
            addTag(
                    tagsByProductId,
                    productTradeType.getProduct().getId(),
                    productTradeType.getTradeType().getName()
            );
        }

        List<ProductPriceInfo> productPriceInfos =
                productPriceInfoRepository.findAllWithPriceInfoByProductIds(productIds);

        for (ProductPriceInfo productPriceInfo : productPriceInfos) {
            addTag(
                    tagsByProductId,
                    productPriceInfo.getProduct().getId(),
                    productPriceInfo.getPriceInfo().getName()
            );
        }

        return tagsByProductId;
    }

    private void addTag(Map<Long, List<String>> tagsByProductId, Long productId, String tagName) {
        tagsByProductId
                .computeIfAbsent(productId, id -> new ArrayList<>())
                .add(tagName);
    }
}