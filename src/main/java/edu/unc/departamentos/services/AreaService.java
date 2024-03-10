package edu.unc.departamentos.services;
import java.util.List;

import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;

public interface AreaService {

	List<Area> listarAreas();
	Area buscarAreaById(Long IdArea)throws EntityNotFoundException ;
	Area grabarArea (Area area) throws IllegalOperationException;
	Area actualizarArea(Long IdArea, Area area) throws EntityNotFoundException, IllegalOperationException;
	void eliminarArea(Long IdArea) throws EntityNotFoundException, IllegalOperationException;
}
