package edu.unc.departamentos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.departamentos.Auth.AuthResponse;
import edu.unc.departamentos.Auth.AuthService;
import edu.unc.departamentos.Auth.LoginRequest;
import edu.unc.departamentos.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;

/**
 * Controlador para manejar las solicitudes de autenticación y registro.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	/**
     * Maneja las solicitudes de inicio de sesión.
     * @param request Solicitud de inicio de sesión.
     * @return ResponseEntity con la respuesta de autenticación.
     */
	@PostMapping(value = "Login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	/**
     * Maneja las solicitudes de registro de nuevos usuarios.
     * @param request Solicitud de registro.
     * @return ResponseEntity con la respuesta de autenticación del usuario registrado.
     */
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
}
