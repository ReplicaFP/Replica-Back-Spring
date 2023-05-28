package com.salesianas.dam.replica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication

public class ReplicaFpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplicaFpApplication.class, args);
	}

}
