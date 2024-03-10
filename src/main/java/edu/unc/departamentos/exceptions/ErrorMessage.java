package edu.unc.departamentos.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase representa un mensaje de error que puede ser devuelto por el sistema en caso de errores o excepciones.
 * Contiene información sobre el código de estado, la marca de tiempo, el mensaje y la descripción del error.
 */
@Getter
@Setter
public class ErrorMessage {
	/**
    * El código de estado HTTP asociado con el error.
    */
	private int statusCode;
	
	 /**
     * La marca de tiempo en la que ocurrió el error.
     */
	private LocalDateTime timestamp;
	
	/**
     * El mensaje descriptivo del error.
     */
	private String message;
	
	 /**
     * Una descripción detallada del error.
     */
	private String description;

	/**
     * Constructor que acepta el código de estado, el mensaje y la descripción del error.
     * @param statusCode El código de estado HTTP asociado con el error.
     * @param message El mensaje descriptivo del error.
     * @param description Una descripción detallada del error.
     */
	public ErrorMessage(HttpStatus statusCode, String message, String description) {
		// Establece el código de estado, la marca de tiempo, el mensaje y la
		// descripción del error.
		this.statusCode = statusCode.value();
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.description = description;
	}
}
