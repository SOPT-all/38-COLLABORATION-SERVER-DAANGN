package org.sopt.daangn.domain.product.exception;

import org.sopt.daangn.domain.product.controller.code.FailureCode;
import org.sopt.daangn.global.exception.BaseException;

public class ProductNotFoundException extends BaseException {
	public ProductNotFoundException() {
		super(FailureCode.PRODUCT_NOTFOUND);
	}
}
