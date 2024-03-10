package edu.unc.departamentos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una respuesta de autenticación.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	/**
     * Token de autenticación.
     */
	String token;
}
