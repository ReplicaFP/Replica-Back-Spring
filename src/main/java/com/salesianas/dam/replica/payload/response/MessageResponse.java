package com.salesianas.dam.replica.payload.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Clase de respuesta al registro de usuario
 * 
 * @author rblancar
 *
 */
@Getter
@Setter
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {

		this.message = message;

	}


}