package edu.unc.departamentos.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Especialidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdEspecialidad;
	private String nombreEspecialidad;
	
	@ManyToOne
	@JsonBackReference
	//@JoinColumn(name ="especialidades")
	private Departamento departamento;
}
