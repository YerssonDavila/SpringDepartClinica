package edu.unc.departamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.departamentos.domain.Departamento;
import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.repositories.DepartamentoRepository;
import edu.unc.departamentos.repositories.AreaRepository;

@Service
public class DepartamentoServiceImp implements DepartamentoService {

	@Autowired
	private DepartamentoRepository depaR;

	@Autowired
	private AreaRepository espeR;

	@Override
	public List<Departamento> listarDepartameentos() {
		// TODO Auto-generated method stub
		return depaR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento buscarDepaById(Long IdDepa) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		Optional<Departamento> depa = depaR.findById(IdDepa);
		if (depa.isEmpty()) {
			throw new EntityNotFoundException("La Departamento con el ID:" + IdDepa + "  no se encontró.");
		}
		return depa.get();
	}

	@Override
	@Transactional
	public Departamento grabarDepa(Departamento depa) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return depaR.save(depa);
	}

	@Override
	public Departamento actualizarDepa(Long IdDepa, Departamento depa)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		Optional<Departamento> depaEntity = depaR.findById(IdDepa);
		if (depaEntity.isEmpty())
			throw new EntityNotFoundException("La cita con id proporcionado no fue encontrado");

		depa.setIdDepartamento(IdDepa);
		return depaR.save(depa);

	}

	@Override
	public void eliminarDepa(Long IdDepa) throws EntityNotFoundException, IllegalOperationException {
		Departamento depapart = depaR.findById(IdDepa).orElseThrow(
				() -> new EntityNotFoundException("La Departamento con el ID:" + IdDepa + "  no se encontró."));

		depaR.deleteById(IdDepa);

	}

	@Override
	@Transactional
	public Departamento asignarEspDepa(Long IdDepa, Long IdEspe)
			throws EntityNotFoundException, IllegalOperationException {
		// TODO Auto-generated method stub
		Area espeEntity = espeR.findById(IdEspe).orElseThrow(
				() -> new EntityNotFoundException("La especialidad con este id proporcionado no existe en la BD"));

		Departamento depaEntity = depaR.findById(IdDepa).orElseThrow(
				() -> new EntityNotFoundException("El departamento con este id proporcionado no existe en la BD"));
		espeEntity.setDepartamento(depaEntity);

		depaR.save(depaEntity);

		return depaEntity;

	}

}
