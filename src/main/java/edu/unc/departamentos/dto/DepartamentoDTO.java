package edu.unc.departamentos.dto;

import java.util.ArrayList;
import java.util.List;

import edu.unc.departamentos.domain.Especialidad;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartamentoDTO {

	private Long IdDepartamento;
	private String nombreDepartamento;
	private List<Especialidad>  especialidades = new ArrayList<>();
}
