package edu.unc.departamentos.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

	private int statusCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;

	public ErrorMessage(HttpStatus statusCode, String message, String description) {
		// Establece el código de estado, la marca de tiempo, el mensaje y la
		// descripción del error.
		this.statusCode = statusCode.value();
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.description = description;
	}
}
