package edu.unc.departamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DepartamentosClinicaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartamentosClinicaServiceApplication.class, args);
	}

}
