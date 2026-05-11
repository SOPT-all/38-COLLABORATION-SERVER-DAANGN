package org.sopt.daangn.global.api.status;

import org.springframework.http.HttpStatus;

public interface Status {
	HttpStatus getHttpStatus();

	String getMessage();
}
