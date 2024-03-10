package edu.unc.departamentos.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * Clase que representa un departamento en el sistema.
 */
@Data
@Entity
public class Departamento extends RepresentationModel<Departamento>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdDepartamento;
	
	/**
     * Nombre del departamento.
     */
	private String nombreDepartamento;
	
	/**
     * Lista de areas asociadas al departamento.
     */
	@OneToMany(mappedBy="departamento")
	@JsonManagedReference
	private List<Area>  area = new ArrayList<>();
	
}
