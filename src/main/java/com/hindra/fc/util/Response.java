package com.hindra.fc.util;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Response<T> {

    private Boolean status;
	private String message;
	private T data;

	public Response() {
	}

	public Response(Boolean status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
