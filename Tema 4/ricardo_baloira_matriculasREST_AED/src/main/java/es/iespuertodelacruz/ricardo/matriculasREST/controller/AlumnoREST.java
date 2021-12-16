package es.iespuertodelacruz.ricardo.matriculasREST.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.dto.AlumnoDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AlumnosService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoREST {
	private Logger logger = LoggerFactory.getLogger(AlumnoREST.class);
	
	@Autowired
	AlumnosService alumnosService;
	
	@Autowired
	MatriculasService matriculasService;
	
	@GetMapping	
	public List<Alumno> getAll(){
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnosService
		.findAll()
		.forEach(a -> alumnos.add((Alumno) a) );
	return alumnos;
	}
	
	/*
	@GetMapping	
	public Collection<AlumnoDTO> getAll(){
		List alumnos = new ArrayList<Alumno>();
		for(Alumno a: alumnosService.findAll()) {
			alumnos.add(new AlumnoDTO(a));
		}
		return alumnos;
	}
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(new AlumnoDTO(optAlumno.get()));
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	/*
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AlumnoDTO alumnoDTO){
		Alumno a = new Alumno();
		a.setNombre(alumnoDTO.getNombre());
		a.setApellidos(alumnoDTO.getApellidos());
		a.setDni(alumnoDTO.getDni());
		a.setFechanacimiento(alumnoDTO.getFechanacimiento());
		a.setMatriculas(alumnoDTO.getMatriculas());

		
		Optional<Matricula> optMatricula= matriculasService.findById(alumnoDTO.getMatriculas()));
		if( optM.isPresent()) {
			h.setFkidmoneda(optM.get());
			historicocambioeuroService.save(h);
			return ResponseEntity.ok().body(new AlumnoDTO(h));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no existe");
		}
		
	}
	*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		if(optAlumno.isPresent()) {
			alumnosService.deleteById(id);
			return ResponseEntity.ok("Alumno borrado");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro del alumno no existe");
		}

	}
	
	/*
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody HistoricocambioeuroDTO hDTO){
		Optional<Historicocambioeuro> optH = historicocambioeuroService.findById(id);
		if(optH.isPresent()) {
			Historicocambioeuro h = optH.get();
			h.setEquivalenteeuro(hDTO.getEquivalenteeuro());
			h.setFecha(hDTO.getFecha() );
			Optional<Monedas> optM= monedasService.findById(hDTO.getFkidmoneda());
			if( optM.isPresent()) {
				h.setFkidmoneda(optM.get());
				historicocambioeuroService.save(h);
				return ResponseEntity.ok().body(new HistoricocambioeuroDTO(h));
				
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la moneda referenciada no existe");
			}			
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	*/
}