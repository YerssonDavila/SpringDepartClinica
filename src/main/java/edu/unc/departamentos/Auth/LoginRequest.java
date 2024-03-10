package edu.unc.departamentos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de inicio de sesión.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	/**
     * Nombre de usuario.
     */
	String username;
	 /**
     * Contraseña del usuario.
     */
	String password;
}
