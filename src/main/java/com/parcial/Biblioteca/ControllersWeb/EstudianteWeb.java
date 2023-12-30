package com.parcial.Biblioteca.ControllersWeb;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.Biblioteca.Documents.Estudiante;
import com.parcial.Biblioteca.Repositories.EstudianteRepository;

@RestController
@RequestMapping("estudiante")
public class EstudianteWeb {

	@Autowired
	private EstudianteRepository estudianteRepository;

	@GetMapping("/")
	public List<Estudiante> findAll() {
		return estudianteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Estudiante findById(@PathVariable String id) {
		return estudianteRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public Estudiante save(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
		return estudianteRepository.save(estudiante);
	}

	@PostMapping("/{id}")
	public Estudiante update(@PathVariable String id, Estudiante estudiante) {
		estudiante.setId(id);
		return estudianteRepository.save(estudiante);
	}

	@DeleteMapping("/{id}")
	public Estudiante delete(@PathVariable String id) {
		Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
		estudianteRepository.deleteById(id);
		return estudiante;
	}
}
