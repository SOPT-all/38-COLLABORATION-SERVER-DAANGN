package org.sopt.daangn.domain.product.controller.code;

import org.sopt.daangn.global.api.status.FailureStatus;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FailureCode implements FailureStatus {
	PRODUCT_NOTFOUND(HttpStatus.NOT_FOUND, "해당 상품이 존재하지 않습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
