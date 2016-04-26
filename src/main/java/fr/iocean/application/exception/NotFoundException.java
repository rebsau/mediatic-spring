package fr.iocean.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6882115739398761078L;
	
}
