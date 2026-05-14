package org.sopt.daangn.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sopt.daangn.domain.product.entity.*;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findProductsByFilter(
            String conditionCode,
            String tradeTypeCode,
            String priceInfoCode
    ) {
        QProduct product = QProduct.product;
        QProductItemCondition productItemCondition = QProductItemCondition.productItemCondition;
        QItemCondition itemCondition = QItemCondition.itemCondition;
        QProductTradeType productTradeType = QProductTradeType.productTradeType;
        QTradeType tradeType = QTradeType.tradeType;
        QProductPriceInfo productPriceInfo = QProductPriceInfo.productPriceInfo;
        QPriceInfo priceInfo = QPriceInfo.priceInfo;

        return queryFactory
                .selectDistinct(product)
                .from(product)
                .leftJoin(productItemCondition).on(productItemCondition.product.eq(product))
                .leftJoin(productItemCondition.itemCondition, itemCondition)
                .leftJoin(productTradeType).on(productTradeType.product.eq(product))
                .leftJoin(productTradeType.tradeType, tradeType)
                .leftJoin(productPriceInfo).on(productPriceInfo.product.eq(product))
                .leftJoin(productPriceInfo.priceInfo, priceInfo)
                .where(
                        conditionCodeEq(itemCondition, conditionCode),
                        tradeTypeCodeEq(tradeType, tradeTypeCode),
                        priceInfoCodeEq(priceInfo, priceInfoCode)
                )
                .orderBy(product.lastBumpedAt.desc())
                .fetch();
    }

    private BooleanExpression conditionCodeEq(QItemCondition itemCondition, String conditionCode) {
        return hasText(conditionCode) ? itemCondition.code.eq(conditionCode) : null;
    }

    private BooleanExpression tradeTypeCodeEq(QTradeType tradeType, String tradeTypeCode) {
        return hasText(tradeTypeCode) ? tradeType.code.eq(tradeTypeCode) : null;
    }

    private BooleanExpression priceInfoCodeEq(QPriceInfo priceInfo, String priceInfoCode) {
        return hasText(priceInfoCode) ? priceInfo.code.eq(priceInfoCode) : null;
    }
}
