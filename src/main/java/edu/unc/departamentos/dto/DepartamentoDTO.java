/*
 * @file DepartamentoDTO.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:58:05
 *  
 */
package edu.unc.departamentos.dto;

import java.util.ArrayList;
import java.util.List;

import edu.unc.departamentos.domain.Area;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Esta clase representa un objeto de transferencia de datos (DTO) para un Departamento en un sistema hospitalario.
 * Contiene información sobre el identificador del departamento, su nombre y una lista de áreas asociadas.
 */
@Data
public class DepartamentoDTO {
	/**
     * El identificador único del departamento.
     */
	private Long IdDepartamento;
	
	 /**
     * El nombre descriptivo del departamento.
     */
	private String nombreDepartamento;
	
	 /**
     * Una lista de áreas asociadas al departamento.
     */
	private List<Area>  area = new ArrayList<>();
}
