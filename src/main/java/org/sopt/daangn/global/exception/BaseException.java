package org.sopt.daangn.global.exception;

import org.sopt.daangn.global.api.status.FailureStatus;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
	private final FailureStatus status;

	public BaseException(FailureStatus status) {
		super(status.getMessage());
		this.status = status;
	}
}
