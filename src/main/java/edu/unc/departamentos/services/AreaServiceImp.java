/*
 * @file AreaServiceImp.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:59:08
 *  
 */
package edu.unc.departamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.repositories.AreaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AreaServiceImp.
 */
@Service
public class AreaServiceImp implements AreaService {

	/** The area R. */
	@Autowired
	private AreaRepository areaR;

	/**
	 * Listar areas.
	 *
	 * @return the list
	 */
	@Override
	@Transactional
	public List<Area> listarAreas() {
		// TODO Auto-generated method stub
		return areaR.findAll();
	}

	/**
	 * Buscar area by id.
	 *
	 * @param IdArea the id area
	 * @return the area
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@Override
	@Transactional(readOnly = true)
	public Area buscarAreaById(Long IdArea) throws EntityNotFoundException {
		Optional<Area> area = areaR.findById(IdArea);
		return area.get();

	}

	/**
	 * Grabar area.
	 *
	 * @param area the area
	 * @return the area
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Area grabarArea(Area area) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return areaR.save(area);
	}

	/**
	 * Actualizar area.
	 *
	 * @param IdArea the id area
	 * @param area the area
	 * @return the area
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Area actualizarArea(Long IdArea, Area area)
			throws EntityNotFoundException, IllegalOperationException {
		
		area.setIdArea(IdArea);
		return areaR.save(area);
	}

	/**
	 * Eliminar area.
	 *
	 * @param IdArea the id area
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public void eliminarArea(Long IdArea) throws EntityNotFoundException, IllegalOperationException {
		Area area = areaR.findById(IdArea).orElseThrow(() -> new EntityNotFoundException(
				"La especialidad con el ID:" + IdArea + " proporcionado no se encontró."));
		areaR.deleteById(IdArea);
	}
}
