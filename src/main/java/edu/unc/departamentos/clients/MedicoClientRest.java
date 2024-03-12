package edu.unc.departamentos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microServicesClinicas", url = "localhost:8080/api/medicos")
public interface MedicoClientRest {

	 @DeleteMapping("/eliminar-medico-departamento/{id}")
	 void eliminarMedicoDepaPorId(@PathVariable Long id);
		 
}
