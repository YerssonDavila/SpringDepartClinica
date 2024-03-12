/*
 * @file AreaController.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 2:00:29
 *  
 */
package edu.unc.departamentos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.departamentos.domain.Area;
import edu.unc.departamentos.dto.AreaDTO;
import edu.unc.departamentos.exceptions.EntityNotFoundException;
import edu.unc.departamentos.exceptions.IllegalOperationException;
import edu.unc.departamentos.services.AreaService;
import edu.unc.departamentos.util.ApiResponse;
import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * Controlador para gestionar las operaciones relacionadas con las especialidades.
 */
@RestController
@RequestMapping(value="api/areas", headers = "Api-Version=1")
public class AreaController {

	/** The area S. */
	@Autowired
	private AreaService areaS;	
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;
	

	/**
	 * Obtener areas.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> obtenerAreas() {
		List<Area> area = areaS.listarAreas();

		if (area == null || area.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
			
           for(Area areaD:area) {            	
            	areaD.add(linkTo(methodOn(AreaController.class).obtenerAreaPorId(areaD.getIdArea())).withSelfRel());
	            areaD.add(linkTo(methodOn(AreaController.class).obtenerAreas()).withRel(IanaLinkRelations.COLLECTION));
            }
            CollectionModel<Area> modelo = CollectionModel.of(area);
           modelo.add(linkTo(methodOn(AreaController.class).obtenerAreas()).withSelfRel());
            return new ResponseEntity<>(area, HttpStatus.OK);
	}

	/**
	 * Obtener area por id.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerAreaPorId(@PathVariable Long id) throws EntityNotFoundException {

		Area area = areaS.buscarAreaById(id);
		AreaDTO areaDto = modelMapper.map(area, AreaDTO.class);
		ApiResponse<AreaDTO> response = new ApiResponse<>(true, "Lista de Areas", areaDto);
		area.add(linkTo(methodOn(AreaController.class).obtenerAreaPorId(area.getIdArea())).withSelfRel());
		return ResponseEntity.ok(response);
	}

	/**
	 * Guardar area.
	 *
	 * @param area the area
	 * @param result the result
	 * @return the response entity
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@PostMapping
	public ResponseEntity<?> guardarArea(@Valid @RequestBody Area area, BindingResult result)
			throws IllegalOperationException {

		if (result.hasErrors()) {
			return validar(result);
		}
		Area nuevoArea = modelMapper.map(area, Area.class);
		areaS.grabarArea(nuevoArea);
		AreaDTO saveAreaDto = modelMapper.map(nuevoArea, AreaDTO.class);
		ApiResponse<AreaDTO> response = new ApiResponse<>(true, "Area guardada", saveAreaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	/**
	 * Actualizar area.
	 *
	 * @param areaDto the area dto
	 * @param result the result
	 * @param id the id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<AreaDTO>> actualizarArea(@Valid @RequestBody AreaDTO areaDto,
			BindingResult result, @PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {

		Area areaActualizado = modelMapper.map(areaDto, Area.class);
		areaS.actualizarArea(id, areaActualizado);
		AreaDTO updateArea = modelMapper.map(areaActualizado, AreaDTO.class);
		ApiResponse<AreaDTO> response = new ApiResponse<>(true, "Area actualizada", updateArea);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	/**
	 * Eliminar area.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws EntityNotFoundException the entity not found exception
	 * @throws IllegalOperationException the illegal operation exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarArea(@PathVariable Long id)
			throws EntityNotFoundException, IllegalOperationException {
		areaS.eliminarArea(id);
		ApiResponse<?> response = new ApiResponse<>(true, "Area eliminada con exito", null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	
	
	/**
	 * Validar.
	 *
	 * @param result the result
	 * @return the response entity
	 */
	private ResponseEntity<?> validar(BindingResult result) {
		// TODO Auto-generated method stub
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);	
	}	
}
