package edu.unc.departamentos.services;
import java.util.List;

import edu.unc.departamentos.domain.Especialidad;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;

public interface EspecialidadService {

	List<Especialidad> listarEspecialidades();
	Especialidad buscarEspById(Long IdEspecialidad)throws EntityNotFoundException ;
	Especialidad grabarEspe (Especialidad espe) throws IllegalOperationException;
	Especialidad actualizarEspe(Long IdEspe, Especialidad espe) throws EntityNotFoundException, IllegalOperationException;
	void eliminarEspe(Long IdEspe) throws EntityNotFoundException, IllegalOperationException;
}
