/*
 * @file AreaService.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:59:04
 *  
 */
package edu.unc.departamentos.services;
import java.util.List;

import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;

// TODO: Auto-generated Javadoc
/**
 * The Interface AreaService.
 */
public interface AreaService {

	/**
	 * Listar areas.
	 *
	 * @return the list
	 */
	List<Area> listarAreas();
	
	/**
	 * Buscar area by id.
	 *
	 * @param IdArea the id area
	 * @return the area
	 * @throws EntityNotFoundException the entity not found exception
	 */
	Area buscarAreaById(Long IdArea)throws EntityNotFoundException ;
	
	/**
	 * Grabar area.
	 *
	 * @param area the area
	 * @return the area
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Area grabarArea (Area area) throws IllegalOperationException;
	
	/**
	 * Actualizar area.
	 *
	 * @param IdArea the id area
	 * @param area the area
	 * @return the area
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Area actualizarArea(Long IdArea, Area area) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Eliminar area.
	 *
	 * @param IdArea the id area
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	void eliminarArea(Long IdArea) throws EntityNotFoundException, IllegalOperationException;
}
