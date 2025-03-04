package com.es.emailsender.pojo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomeResponse {

	private String message;
	private HttpStatus httpStatus;
	private boolean isSuccess = false;
}
