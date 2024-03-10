package edu.unc.departamentos.dto;

import edu.unc.departamentos.domain.Departamento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EspecialidadDTO {

	private Long IdEspecialidad;
	
	private String nombreEspecialidad;
	private Departamento departamento;
}
