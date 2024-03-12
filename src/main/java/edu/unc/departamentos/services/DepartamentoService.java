/*
 * @file DepartamentoService.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:59:16
 *  
 */
package edu.unc.departamentos.services;

import java.util.List;
import java.util.Optional;

import edu.unc.departamentos.domain.Departamento;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;

// TODO: Auto-generated Javadoc
/**
 * The Interface DepartamentoService.
 */
public interface DepartamentoService {

	/**
	 * Listar departameentos.
	 *
	 * @return the list
	 */
	List<Departamento> listarDepartameentos();
	
	/**
	 * Buscar depa by id.
	 *
	 * @param IdDepa the id depa
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 */
	Departamento buscarDepaById(Long IdDepa)throws EntityNotFoundException ;
	
	/**
	 * Search.
	 *
	 * @param IdDepa the id depa
	 * @return the optional
	 */
	Optional<Departamento>search(Long IdDepa);
	
	/**
	 * Grabar depa.
	 *
	 * @param depa the depa
	 * @return the departamento
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Departamento grabarDepa (Departamento depa) throws IllegalOperationException;
	
	/**
	 * Actualizar depa.
	 *
	 * @param IdDepa the id depa
	 * @param depa the depa
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Departamento actualizarDepa(Long IdDepa, Departamento depa) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Eliminar depa.
	 *
	 * @param IdDepa the id depa
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	void eliminarDepa(Long IdDepa) throws EntityNotFoundException, IllegalOperationException;
	
	/**
	 * Asignar esp depa.
	 *
	 * @param IdDepa the id depa
	 * @param IdEspe the id espe
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	Departamento asignarEspDepa(Long IdDepa, Long IdEspe) throws EntityNotFoundException, IllegalOperationException;


	/**
	 * Eliminar medico.
	 *
	 * @param id the id
	 */
	void eliminarMedico(Long id);
}
