package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"main", "ApiVehiculos", "ApiEspacioParqueadero"})
public class SistemaParqueaderoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaParqueaderoBackendApplication.class, args);
	}

}
