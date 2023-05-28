package com.salesianas.dam.replica.utils.security.services;

import com.salesianas.dam.replica.persistence.entity.UserEntity;
import com.salesianas.dam.replica.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




/**
 * 
 * Clase de implementación del servicio de implementación de los detalles
 * 
 * @author rblancar
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con username: " + username));
		return UserDetailsImpl.build(user);
	}
}