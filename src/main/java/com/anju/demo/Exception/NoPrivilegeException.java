package com.anju.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NoPrivilegeException extends RuntimeException {
	public NoPrivilegeException(String message){
		super(message);
	}
}
