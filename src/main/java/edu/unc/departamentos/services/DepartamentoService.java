package edu.unc.departamentos.services;

import java.util.List;

import edu.unc.departamentos.domain.Departamento;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;

public interface DepartamentoService {

	List<Departamento> listarDepartameentos();
	Departamento buscarDepaById(Long IdDepa)throws EntityNotFoundException ;
	Departamento grabarDepa (Departamento depa) throws IllegalOperationException;
	Departamento actualizarDepa(Long IdDepa, Departamento depa) throws EntityNotFoundException, IllegalOperationException;
	void eliminarDepa(Long IdDepa) throws EntityNotFoundException, IllegalOperationException;
	Departamento asignarEspDepa(Long IdDepa, Long IdEspe) throws EntityNotFoundException, IllegalOperationException;
}
