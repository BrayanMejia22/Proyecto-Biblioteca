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
import com.parcial.Biblioteca.Documents.Libro;
import com.parcial.Biblioteca.Repositories.LibroRepository;

@RestController
@RequestMapping("libro")
public class LibroWeb {

	@Autowired
	private LibroRepository libroRepository;

	@GetMapping("/")
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	@GetMapping("/{id}")
	public Libro findById(@PathVariable String id) {
		return libroRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public Libro save(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Libro libro = mapper.convertValue(body, Libro.class);
		return libroRepository.save(libro);
	}

	@PostMapping("/{id}")
	public Libro update(@PathVariable String id, Libro libro) {
		libro.setId(id);
		return libroRepository.save(libro);
	}

	@DeleteMapping("/{id}")
	public Libro delete(@PathVariable String id) {
		Libro libro = libroRepository.findById(id).orElse(null);
		libroRepository.deleteById(id);
		return libro;
	}
}
