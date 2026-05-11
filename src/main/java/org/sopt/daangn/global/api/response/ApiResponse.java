package org.sopt.daangn.global.api.response;

import org.sopt.daangn.global.api.status.FailureStatus;
import org.sopt.daangn.global.api.status.SuccessStatus;

public record ApiResponse<T>(
		boolean success,
		String status,
		String message,
		T data
) {
	public static <T> ApiResponse<T> success(SuccessStatus status, T data) {
		return new ApiResponse<>(true, status.toString(), status.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(SuccessStatus status) {
		return new ApiResponse<>(true, status.toString(), status.getMessage(), null);
	}

	public static <T> ApiResponse<T> failure(FailureStatus status) {
		return new ApiResponse<>(false, status.toString(), status.getMessage(), null);
	}
}
