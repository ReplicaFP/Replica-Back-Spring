package com.salesianas.dam.replica.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 
 * Clase de Dto de la respuesta al inicio de sesión
 * 
 * @author rblancar
 *
 */
@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {

		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}