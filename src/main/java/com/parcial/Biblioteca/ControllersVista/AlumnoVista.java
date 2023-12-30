package com.parcial.Biblioteca.ControllersVista;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.Biblioteca.Documents.Categoria;
import com.parcial.Biblioteca.Documents.Libro;
import com.parcial.Biblioteca.Repositories.CategoriaRepository;
import com.parcial.Biblioteca.Repositories.LibroRepository;

@Controller
@RequestMapping("alumno")
public class AlumnoVista {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/PrincipalAlumno")
	public String lista(Model model) {
		List<Libro> libros = libroRepository.findAll();
		List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
		model.addAttribute("libros", libros);
		model.addAttribute("categorias", categorias);
		return "Alumno/Alumno";
	}
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<byte[]> imagen(@PathVariable String id){
		Optional<Libro> libroOptional = libroRepository.findById(id);
		
		if (libroOptional.isPresent()) {
			Libro libro = libroOptional.get();
			byte[] imagenBytes = libro.getImagen();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			return new ResponseEntity<>(imagenBytes, headers, HttpStatus.OK);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
