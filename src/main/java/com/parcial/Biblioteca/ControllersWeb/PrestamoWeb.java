package com.parcial.Biblioteca.ControllersWeb;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.Biblioteca.Documents.Prestamo;
import com.parcial.Biblioteca.Repositories.PrestamoRepository;

@RestController
@RequestMapping("prestamo")
public class PrestamoWeb {

	@Autowired
	private PrestamoRepository prestamoRepository;

	@GetMapping("/")
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Prestamo> findById(@PathVariable String id) {
		return prestamoRepository.findById(id);
	}

	@PostMapping("/")
	public Prestamo save(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Prestamo prestamo = mapper.convertValue(body, Prestamo.class);
		return prestamoRepository.save(prestamo);
	}

	@PostMapping("/{id}")
	public Prestamo update(@PathVariable String id, Prestamo prestamo) {
		prestamo.setId(id);
		return prestamoRepository.save(prestamo);
	}

	@DeleteMapping("/{id}")
	public Prestamo delete(@PathVariable String id) {
		Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
		prestamoRepository.deleteById(id);
		return prestamo;
	}
}
