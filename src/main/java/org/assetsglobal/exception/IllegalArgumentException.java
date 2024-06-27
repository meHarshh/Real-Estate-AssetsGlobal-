package org.assetsglobal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
public class IllegalArgumentException extends RuntimeException {

	

	private String message;
	
}
