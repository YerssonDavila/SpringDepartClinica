package edu.unc.departamentos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de registro de usuario.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * Nombre de usuario.
     */
	String username;
	 /**
     * Contraseña del usuario.
     */
	String password;
	 /**
     * Nombre del usuario.
     */
	String firstname;

    /**
     * Apellido del usuario.
     */
	String lastname;
}
