package org.sopt.daangn.global.exception.handler;

import org.sopt.daangn.global.api.code.FailureCode;
import org.sopt.daangn.global.api.response.ApiResponse;
import org.sopt.daangn.global.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException e) {
		return buildErrorResponse(e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
		return buildErrorResponse(FailureCode.INTERNAL_ERROR);
	}
}
