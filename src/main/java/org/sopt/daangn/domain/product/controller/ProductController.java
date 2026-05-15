package org.sopt.daangn.domain.product.controller;

import org.sopt.daangn.domain.product.controller.code.SuccessCode;
import org.sopt.daangn.domain.product.dto.response.AdProductResponse;
import org.sopt.daangn.domain.product.dto.response.CategoryResponse;
import org.sopt.daangn.domain.product.service.ProductService;
import org.sopt.daangn.global.api.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping(path = "/categories")
	public ResponseEntity<ApiResponse<CategoryResponse>> getCategories() {
		return ResponseEntity.status(SuccessCode.CATEGORY_FOUND.getHttpStatus())
				.body(ApiResponse.success(SuccessCode.CATEGORY_FOUND, productService.getCategories()));
	}

    @GetMapping(path = "/ad")
    public ResponseEntity<ApiResponse<List<AdProductResponse>>> getAdProducts() {
        return ResponseEntity.status(SuccessCode.AD_PRODUCTS_FOUND.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.AD_PRODUCTS_FOUND, productService.getAdProducts()));
    }
}
