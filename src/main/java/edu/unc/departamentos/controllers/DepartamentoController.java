package edu.unc.departamentos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.unc.departamentos.domain.Departamento;
import edu.unc.departamentos.dto.DepartamentoDTO;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.services.DepartamentoService;
import edu.unc.departamentos.util.ApiResponse;

/**
 * Controlador para gestionar las operaciones relacionadas con los departamentos.
 */
@RestController
@RequestMapping(value = "api/departamentos", headers = "Api-Version=1")
@JsonPropertyOrder({ "IdDepartamento", "nombreDepartamento", "especialidades" })
public class DepartamentoController {

	@Autowired
	private DepartamentoService depaS;

	@Autowired
	private ModelMapper modelMapper;

	/**
     * Obtiene todos los departamentos.
     * @return ResponseEntity con la lista de departamentos.
     */
	@GetMapping
	public ResponseEntity<?> obtenerDepartamentos() {
		List<Departamento> depa = depaS.listarDepartameentos();
		if (depa == null || depa.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		
		/*else {
			List<DepartamentoDTO> depaDto = depa.stream().map(espec -> modelMapper.map(espec, DepartamentoDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<DepartamentoDTO>> response = new ApiResponse<>(true, "Lista de Departamentos", depaDto);
			return ResponseEntity.ok(response);
		}*/
		
		for(Departamento departamento:depa) {        	
        	departamento.add(linkTo(methodOn(DepartamentoController.class).obtenerDepartamentosPorId(departamento.getIdDepartamento())).withSelfRel());
            departamento.add(linkTo(methodOn(DepartamentoController.class).obtenerDepartamentos()).withRel(IanaLinkRelations.COLLECTION));
        }
        CollectionModel<Departamento> modelo = CollectionModel.of(depa);
       modelo.add(linkTo(methodOn(DepartamentoController.class).obtenerDepartamentos()).withSelfRel());
        return new ResponseEntity<>(depa, HttpStatus.OK);
}
	
	/**
     * Obtiene un departamento por su ID.
     * @param id ID del departamento.
     * @return ResponseEntity con el departamento encontrado.
     * @throws EntityNotFoundException Si no se encuentra el departamento.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDepartamentosPorId(@PathVariable Long id) throws EntityNotFoundException {
		Departamento dpto = depaS.buscarDepaById(id);
		DepartamentoDTO depaDto = modelMapper.map(dpto, DepartamentoDTO.class);
		ApiResponse<DepartamentoDTO> response = new ApiResponse<>(true, "Lista de Departamentos", depaDto);
		dpto.add(linkTo(methodOn(DepartamentoController.class).obtenerDepartamentosPorId(dpto.getIdDepartamento())).withSelfRel());
		return ResponseEntity.ok(response);
	}

	// CAMBIE DE DEPARTAMENTO A DEPARTAMENTODTO
	  /**
     * Guarda un nuevo departamento.
     * @param depa DTO del departamento a guardar.
     * @param result Resultado de la validación.
     * @return ResponseEntity con la respuesta del guardado.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
	@PostMapping
	public ResponseEntity<?> guardarDepartamento(@Valid @RequestBody DepartamentoDTO depa, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		Departamento nuevoDpto = modelMapper.map(depa, Departamento.class);
		depaS.grabarDepa(nuevoDpto);
		DepartamentoDTO saveDepaDto = modelMapper.map(nuevoDpto, DepartamentoDTO.class);
		ApiResponse<DepartamentoDTO> response = new ApiResponse<>(true, "Departamento guardada", saveDepaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	

    /**
     * Actualiza un departamento existente.
     * @param depaDto DTO del departamento a actualizar.
     * @param result Resultado de la validación.
     * @param id ID del departamento a actualizar.
     * @return ResponseEntity con la respuesta de la actualización.
     * @throws EntityNotFoundException Si no se encuentra el departamento.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartamentoDTO>> actualizarDepartamento(
			@Valid @RequestBody DepartamentoDTO depaDto, BindingResult result, @PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {

		Departamento depActualizada = modelMapper.map(depaDto, Departamento.class);
		depaS.actualizarDepa(id, depActualizada);
		DepartamentoDTO updateDep = modelMapper.map(depActualizada, DepartamentoDTO.class);
		ApiResponse<DepartamentoDTO> response = new ApiResponse<>(true, "Departamento actualizado", updateDep);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	 /**
     * Elimina un departamento.
     * @param id ID del departamento a eliminar.
     * @return ResponseEntity con la respuesta de la eliminación.
     * @throws EntityNotFoundException Si no se encuentra el departamento.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		depaS.eliminarDepa(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Especialidad eliminada con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
     * Método privado para manejar la validación.
     * @param result Resultado de la validación.
     * @return ResponseEntity con los errores de validación.
     */
	private ResponseEntity<?> validar(BindingResult result) {
		// TODO Auto-generated method stub
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);

	}
	
	 /**
     * Asigna una especialidad a un departamento.
     * @param IdDpto ID del departamento.
     * @param IdEsp ID de la especialidad.
     * @return ResponseEntity con la respuesta de la asignación.
     * @throws EntityNotFoundException Si no se encuentra el departamento o la especialidad.
     * @throws IllegalOperationException Si se produce una operación ilegal.
     */
	@PatchMapping("/{IdDpto}/asignarEspecialidad/{IdEsp}")
	public ResponseEntity<?> asignarEspecialidad(@PathVariable Long IdDpto, @PathVariable Long IdEsp)
			throws EntityNotFoundException, IllegalOperationException {
		try {

			Departamento departamento = depaS.asignarEspDepa(IdDpto, IdEsp);
			return ResponseEntity.ok(departamento);
		 } catch (EntityNotFoundException e) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el departamento o la especialidad");
		    } catch (IllegalOperationException e) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operación ilegal durante la asignación de especialidad");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Error durante la asignación de especialidad");
		    }
	}

}
