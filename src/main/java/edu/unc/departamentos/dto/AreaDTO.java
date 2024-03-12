/*
 * @file AreaDTO.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:58:09
 *  
 */
package edu.unc.departamentos.dto;

import edu.unc.departamentos.domain.Departamento;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Esta clase representa un objeto de transferencia de datos (DTO) para una Área en un sistema hospitalario.
 * Contiene información sobre el identificador del área, su nombre y el departamento al que está asociado.
 */
@Data
public class AreaDTO {

	 /**
     * El identificador único del área.
     */
	private Long IdArea;
	
	 /**
     * El nombre descriptivo del área.
     */
	private String nombreArea;
	
	 /**
     * El departamento al que está asociado el área.
     */
	private Departamento departamento;
}
