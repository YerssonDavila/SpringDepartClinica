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

@Service
public class AreaServiceImp implements AreaService {

	@Autowired
	private AreaRepository areaR;

	@Override
	@Transactional
	public List<Area> listarAreas() {
		// TODO Auto-generated method stub
		return areaR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Area buscarAreaById(Long IdArea) throws EntityNotFoundException {
		Optional<Area> area = areaR.findById(IdArea);
		return area.get();

	}

	@Override
	@Transactional
	public Area grabarArea(Area area) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return areaR.save(area);
	}

	@Override
	@Transactional
	public Area actualizarArea(Long IdArea, Area area)
			throws EntityNotFoundException, IllegalOperationException {
		
		area.setIdArea(IdArea);
		return areaR.save(area);
	}

	@Override
	@Transactional
	public void eliminarArea(Long IdArea) throws EntityNotFoundException, IllegalOperationException {
		Area area = areaR.findById(IdArea).orElseThrow(() -> new EntityNotFoundException(
				"La especialidad con el ID:" + IdArea + " proporcionado no se encontró."));
		areaR.deleteById(IdArea);
	}
}
