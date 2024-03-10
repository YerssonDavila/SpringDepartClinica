package edu.unc.departamentos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.departamentos.domain.Especialidad;
import edu.unc.departamentos.dto.EspecialidadDTO;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.services.EspecialidadService;
import edu.unc.departamentos.util.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="api/especialidades", headers = "Api-Version=1")
public class EspecialidadController {

	@Autowired
	private EspecialidadService espeS;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<?> obtenerEspecialidades() {
		List<Especialidad> espec = espeS.listarEspecialidades();

		if (espec == null || espec.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<EspecialidadDTO> espeDTO =espec.stream().map(especi -> modelMapper.map(especi, EspecialidadDTO.class))
					.collect(Collectors.toList());
			ApiResponse<List<EspecialidadDTO>> response = new ApiResponse<>(true, "Lista de especialidades", espeDTO);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Long id) throws EntityNotFoundException {

		Especialidad especialidad = espeS.buscarEspById(id);

		EspecialidadDTO especialidadDto = modelMapper.map(especialidad, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Lista de Especialidades", especialidadDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<?> guardarEspecialidad(@Valid @RequestBody Especialidad espe, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		Especialidad nuevaEsp = modelMapper.map(espe, Especialidad.class);
		espeS.grabarEspe(nuevaEsp);
		EspecialidadDTO saveEspDto = modelMapper.map(nuevaEsp, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad guardada", saveEspDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EspecialidadDTO>> actualizarEspecialidad(@Valid @RequestBody EspecialidadDTO espDto,
			BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {

		Especialidad espActualizada = modelMapper.map(espDto, Especialidad.class);
		espeS.actualizarEspe(id, espActualizada);
		EspecialidadDTO updateEsp = modelMapper.map(espActualizada, EspecialidadDTO.class);
		ApiResponse<EspecialidadDTO> response = new ApiResponse<>(true, "Especialidad actualizada", updateEsp);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		espeS.eliminarEspe(id);
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
	
}
