package edu.unc.departamentos.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.unc.departamentos.JWT.JwtService;
import edu.unc.departamentos.domain.Role;
import edu.unc.departamentos.domain.User;
import edu.unc.departamentos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de autenticación y registro de usuarios.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;

	/**
     * Maneja el proceso de autenticación del usuario.
     * @param request Solicitud de inicio de sesión.
     * @return Respuesta de autenticación con el token JWT.
     * @throws AuthenticationException Si las credenciales de inicio de sesión son inválidas.
     */
	  public AuthResponse login(LoginRequest request) {
	  authManager.authenticate(new
	  UsernamePasswordAuthenticationToken(request.getUsername(),
	  request.getPassword())); 
	  UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(); String
	  token=jwtService.getToken(user); return AuthResponse.builder() .token(token)
	  .build();
	  
	  }
	  
	  /**
	     * Maneja el proceso de registro de un nuevo usuario.
	     * @param request Solicitud de registro.
	     * @return Respuesta de autenticación con el token JWT del usuario registrado.
	     */
	
	  public AuthResponse register(RegisterRequest request) {
		User user = User.builder().username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.role(Role.USER).build();

		userRepository.save(user);

		return AuthResponse.builder().token(jwtService.getToken(user)).build();

	}
}
