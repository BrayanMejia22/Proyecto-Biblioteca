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
import com.parcial.Biblioteca.Documents.Categoria;
import com.parcial.Biblioteca.Repositories.CategoriaRepository;

@RestController
@RequestMapping("categoria")
public class CategoriaWeb {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/")
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Categoria findById(@PathVariable String id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@PostMapping("/")
	public Categoria save(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Categoria autor = mapper.convertValue(body, Categoria.class);
		return categoriaRepository.save(autor);
	}

	@PostMapping("/{id}")
	public Categoria update(@PathVariable String id, Categoria categoria) {
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}

	@DeleteMapping("/{id}")
	public Categoria delete(@PathVariable String id) {
		Categoria autor = categoriaRepository.findById(id).orElse(null);
		categoriaRepository.deleteById(id);
		return autor;
	}
}
