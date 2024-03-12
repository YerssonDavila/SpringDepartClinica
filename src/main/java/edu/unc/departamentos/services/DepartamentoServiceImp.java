/*
 * @file DepartamentoServiceImp.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:59:20
 *  
 */
package edu.unc.departamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.departamentos.domain.Departamento;
import edu.unc.departamentos.clients.MedicoClientRest;
import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.repositories.DepartamentoRepository;
import edu.unc.departamentos.repositories.AreaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DepartamentoServiceImp.
 */
@Service
public class DepartamentoServiceImp implements DepartamentoService {

	/** The depa R. */
	@Autowired
	private DepartamentoRepository depaR;

	/** The espe R. */
	@Autowired
	private AreaRepository espeR;

	/** The med client. */
	@Autowired
	private MedicoClientRest medClient;
	
	/**
	 * Listar departameentos.
	 *
	 * @return the list
	 */
	@Override
	public List<Departamento> listarDepartameentos() {
		// TODO Auto-generated method stub
		return depaR.findAll();
	}

	/**
	 * Buscar depa by id.
	 *
	 * @param IdDepa the id depa
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 */
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

	/**
	 * Grabar depa.
	 *
	 * @param depa the depa
	 * @return the departamento
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	@Transactional
	public Departamento grabarDepa(Departamento depa) throws IllegalOperationException {
		// TODO Auto-generated method stub
		return depaR.save(depa);
	}

	/**
	 * Actualizar depa.
	 *
	 * @param IdDepa the id depa
	 * @param depa the depa
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
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

	/**
	 * Eliminar depa.
	 *
	 * @param IdDepa the id depa
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@Override
	public void eliminarDepa(Long IdDepa) throws EntityNotFoundException, IllegalOperationException {
		Departamento depapart = depaR.findById(IdDepa).orElseThrow(
				() -> new EntityNotFoundException("La Departamento con el ID:" + IdDepa + "  no se encontró."));

		depaR.deleteById(IdDepa);

	}

	/**
	 * Asignar esp depa.
	 *
	 * @param IdDepa the id depa
	 * @param IdEspe the id espe
	 * @return the departamento
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
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
	
	/**
	 * Eliminar medico.
	 *
	 * @param id the id
	 */
	@Override
    @Transactional
    public void eliminarMedico(Long id) {
		medClient.eliminarMedicoDepaPorId(id);
	}

	/**
	 * Search.
	 *
	 * @param IdDepa the id depa
	 * @return the optional
	 */
	@Override
	public Optional<Departamento> search(Long IdDepa) {
		return depaR.findById(IdDepa);
		
	}

}
