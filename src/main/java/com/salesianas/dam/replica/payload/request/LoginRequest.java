package com.salesianas.dam.replica.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 
 * Clase de la petición de Inicio de Sesión
 * 
 * @author rblancar
 *
 */
@Getter
@Setter
public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;


}