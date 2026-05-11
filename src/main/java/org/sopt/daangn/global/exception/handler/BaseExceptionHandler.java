package org.sopt.daangn.global.exception.handler;

import org.sopt.daangn.global.api.status.FailureStatus;
import org.sopt.daangn.global.api.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {
	protected final ResponseEntity<ApiResponse<Void>> buildErrorResponse(FailureStatus status) {
		return ResponseEntity.status(status.getHttpStatus())
				.body(ApiResponse.failure(status));
	}
}
