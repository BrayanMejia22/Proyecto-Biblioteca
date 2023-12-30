package com.parcial.Biblioteca.ControllersVista;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.parcial.Biblioteca.Documents.Categoria;
import com.parcial.Biblioteca.Documents.Libro;
import com.parcial.Biblioteca.Repositories.CategoriaRepository;
import com.parcial.Biblioteca.Repositories.LibroRepository;

@Controller
@RequestMapping("libro")
public class LibroVista {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LibroRepository libroRepository;

	@GetMapping("/VistaListaLibros")
	public String ListaLibros(Model model) {
		List<Libro> libros = libroRepository.findAll();

		model.addAttribute("libros", libros);
		return "Libro/Libros";
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

	@GetMapping("/VistaRegistroLibros")
	public String RegistrarLibro(Model model) {
		List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
		List<String> nombresCategorias = new ArrayList<>();

		for (Categoria categoria : categorias) {
			nombresCategorias.add(categoria.getNombre());
		}
		model.addAttribute("nombresCategorias", nombresCategorias);
		return "Libro/RegistrarLibro";
	}

	@PostMapping("/RegistroLibro")
	public String RegistroLibro(@ModelAttribute("libros") Libro libro, 
			@RequestParam("file") MultipartFile file) {

		try {

			if (!file.isEmpty()) {
				libro.setImagen(file.getBytes());
			}		

			libroRepository.save(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:VistaListaLibros";
	}

	@GetMapping("/VistaBuscarLibro")
	public String ActualizarLibro(Model model) {
		List<Libro> libros = libroRepository.findAll();
		List<String> nombreLibros = new ArrayList<>();

		for (Libro libro : libros) {
			nombreLibros.add(libro.getTitulo());
		}
		model.addAttribute("nombreLibros", nombreLibros);
		return "Libro/BuscarLibro";
	}

	@PostMapping("/BuscarLibro")
	public String BuscarLibro(@RequestParam String nombreLibros, Model model) {
		Libro libroEncontrado = libroRepository.findByTitulo(nombreLibros);
		model.addAttribute("libroEncontrado", libroEncontrado);
		model.addAttribute("id", libroEncontrado.getId());
		model.addAttribute("titulo", libroEncontrado.getTitulo());
		model.addAttribute("autor", libroEncontrado.getAutor());
		model.addAttribute("paginas", libroEncontrado.getPaginas());
		return "Libro/ActualizarLibro";
	}

	@PostMapping("/ActualizarLibro/{id}")
	public String actualizarLibro(@PathVariable("id") String id, @ModelAttribute("libro") Libro libro) {
		Libro libroEncontrado = libroRepository.findById(id).orElse(null);

		if (libroEncontrado != null) {
			libroEncontrado.setTitulo(libro.getTitulo());
			libroEncontrado.setAutor(libro.getAutor());
			libroEncontrado.setPaginas(libro.getPaginas());
			libroRepository.save(libroEncontrado);
		}

		return "redirect:/libro/VistaListaLibros";
	}

	@GetMapping("/EliminarLibro/{id}")
	public String EliminarLibro(@PathVariable("id") String id) {
		libroRepository.deleteById(id);
		return "redirect:/libro/VistaListaLibros";
	}

}
