/*
 * @file Area.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:57:00
 *  
 */
package edu.unc.departamentos.domain;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Clase que representa una especialidad en el sistema.
 */
@Entity
@Data
public class Area extends RepresentationModel<Area> {

	/** The Id area. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdArea;
	/**
     * Nombre del área.
     */
	private String nombreArea;
	
	/**
     * Departamento al que pertenece el área.
     */
	@ManyToOne
	@JsonBackReference
	//@JoinColumn(name ="area")
	private Departamento departamento;
}
