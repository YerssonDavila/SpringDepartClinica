package edu.unc.departamentos.exceptions;

/**
 * Esta clase representa una excepción que se lanza cuando una entidad específica no se encuentra en el sistema.
 * Puede ser lanzada en situaciones donde se esperaba encontrar una entidad pero no está presente.
 */
public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor que acepta un mensaje para describir la excepción.
     * @param message El mensaje que describe la excepción.
     */
	public EntityNotFoundException(String message) {
		super(message);
	}	
}
