package it.prova.cartellaesattoriale.web.api.exception;

public class ContribuenteNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ContribuenteNotFoundException(String message) {
		super(message);
	}

}
