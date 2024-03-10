package edu.unc.departamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.departamentos.domain.Especialidad;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.repositories.EspecialidadRepository;

@Service
public class EspecialidadServiceImp implements EspecialidadService {

	@Autowired
	private EspecialidadRepository espeR;

	@Override
	@Transactional
	public List<Especialidad> listarEspecialidades() {
		// TODO Auto-generated method stub
		return espeR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Especialidad buscarEspById(Long IdEspecialidad) throws EntityNotFoundException {
		Optional<Especialidad> espe = espeR.findById(IdEspecialidad);
		return espe.get();

	}

	@Override
	@Transactional
	public Especialidad grabarEspe(Especialidad espe) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return espeR.save(espe);
	}

	@Override
	@Transactional
	public Especialidad actualizarEspe(Long IdEspe, Especialidad espe)
			throws EntityNotFoundException, IllegalOperationException {
		
		espe.setIdEspecialidad(IdEspe);
		return espeR.save(espe);
	}

	@Override
	@Transactional
	public void eliminarEspe(Long IdEspe) throws EntityNotFoundException, IllegalOperationException {
		Especialidad espe = espeR.findById(IdEspe).orElseThrow(() -> new EntityNotFoundException(
				"La especialidad con el ID:" + IdEspe + " proporcionado no se encontró."));

		espeR.deleteById(IdEspe);
	}

}
