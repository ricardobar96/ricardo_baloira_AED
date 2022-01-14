package es.iespuertodelacruz.ricardo.matriculasREST.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.dto.AlumnoDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AlumnosService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value= "MatriculaRESTv3", description = "REST APIs relacionadas con la entidad Alumno accesibles para un usuario correctamente autenticado")
@RestController
@RequestMapping("/api/v2/alumnos")
public class AlumnoRESTv2 {
	private Logger logger = LoggerFactory.getLogger(AlumnoRESTv2.class);
	
	@Autowired
	AlumnosService alumnosService;
	
	@Autowired
	MatriculasService matriculasService;
	
	@Autowired
	AsignaturasService asignaturasService;
	
	@GetMapping	
	@ApiOperation(value = "Devuelve lista de todos los alumnos", tags = "getAll")
	public Collection<AlumnoDTO> getAll(@RequestParam(required=false, name="nombre")String nombre){
		List alumnos = new ArrayList<Alumno>();
		
		if(nombre!=null) {
			alumnos.add(alumnosService.findByNombre(nombre));
		}
		else {
			for(Alumno a: alumnosService.findAll()) {
				alumnos.add(new AlumnoDTO(a));
				}
		}
		return alumnos;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca un alumno por su DNI", tags = "get")
	@ApiImplicitParam(name = "id", value = "DNI del alumno", required = true, dataType = "string", paramType = "query")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(new AlumnoDTO(optAlumno.get()));
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
}

