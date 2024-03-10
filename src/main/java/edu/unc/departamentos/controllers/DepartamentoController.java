package edu.unc.departamentos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "api/departamentos", headers = "Api-Version=1")
@JsonPropertyOrder({ "IdDepartamento", "nombreDepartamento", "especialidades" })
public class DepartamentoController {

	@Autowired
	private DepartamentoService depaS;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> obtenerDepartamentos() {

		List<Departamento> depa = depaS.listarDepartameentos();
		if (depa == null || depa.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<DepartamentoDTO> depaDto = depa.stream().map(espec -> modelMapper.map(espec, DepartamentoDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<DepartamentoDTO>> response = new ApiResponse<>(true, "Lista de Departamentos", depaDto);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerDepartamentosPorId(@PathVariable Long id) throws EntityNotFoundException {

		Departamento dpto = depaS.buscarDepaById(id);

		DepartamentoDTO depaDto = modelMapper.map(dpto, DepartamentoDTO.class);
		ApiResponse<DepartamentoDTO> response = new ApiResponse<>(true, "Lista de Departamentos", depaDto);
		return ResponseEntity.ok(response);
	}

	// CAMBIE DE DEPARTAMENTO A DEPARTAMENTODTO
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		depaS.eliminarDepa(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Especialidad eliminada con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	private ResponseEntity<?> validar(BindingResult result) {
		// TODO Auto-generated method stub
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);

	}

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
