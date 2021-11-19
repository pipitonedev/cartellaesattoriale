package it.prova.cartellaesattoriale.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ContribuenteConCartellaEsattorialeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ContribuenteConCartellaEsattorialeException(String message) {
		super(message);
	}

}
