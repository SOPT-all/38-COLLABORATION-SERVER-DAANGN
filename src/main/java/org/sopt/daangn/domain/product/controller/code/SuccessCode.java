package org.sopt.daangn.domain.product.controller.code;

import org.sopt.daangn.global.api.status.SuccessStatus;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements SuccessStatus {
	PRODUCT_FOUND(HttpStatus.OK, "상품 조회가 완료되었습니다."),
	CATEGORY_FOUND(HttpStatus.OK, "카테고리 조회가 완료되었습니다."),
    AD_PRODUCTS_FOUND(HttpStatus.OK, "광고 상품 조회가 완료되었습니다.");

    private final HttpStatus httpStatus;
	private final String message;
}
