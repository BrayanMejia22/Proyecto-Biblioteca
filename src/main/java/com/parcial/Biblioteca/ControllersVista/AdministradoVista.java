package com.parcial.Biblioteca.ControllersVista;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.Biblioteca.Documents.Categoria;
import com.parcial.Biblioteca.Repositories.CategoriaRepository;
import com.parcial.Biblioteca.Repositories.LibroRepository;
import com.parcial.Biblioteca.Repositories.PrestamoDevolucionRepository;
import com.parcial.Biblioteca.Repositories.PrestamoRepository;

@Controller
@RequestMapping("administrador")
public class AdministradoVista {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private PrestamoRepository prestamoRepository;

	@Autowired
	private PrestamoDevolucionRepository prestamoDevolucionRepository;
	
	@GetMapping("/regresar")
	public String regresar(Model model) {

		long totalLibros = libroRepository.count();
		model.addAttribute("totalLibros", totalLibros);

		long totalPrestamos = prestamoRepository.count();
		model.addAttribute("totalPrestamos", totalPrestamos);

		long totalCategorias = categoriaRepository.count();
		model.addAttribute("totalCategorias", totalCategorias);

		long prestamoDevolucion = prestamoDevolucionRepository.count();
		model.addAttribute("prestamoDevolucion", prestamoDevolucion);

		return "Administrador/principal";
	}

	@GetMapping("/Principal")
	public String Principal(Model model) {

		long totalLibros = libroRepository.count();
		model.addAttribute("totalLibros", totalLibros);

		long totalPrestamos = prestamoRepository.count();
		model.addAttribute("totalPrestamos", totalPrestamos);

		long totalCategorias = categoriaRepository.count();
		model.addAttribute("totalCategorias", totalCategorias);

		long prestamoDevolucion = prestamoDevolucionRepository.count();
		model.addAttribute("prestamoDevolucion", prestamoDevolucion);
		
		return "Administrador/principal";
	}

	// categorias
	// envia a la vista categorias
	@GetMapping("/VistaRegistroCategoria")
	public String Categoria() {
		return "Administrador/Categoria";
	}

	@PostMapping("/registroCategoria")
	public String registroCategoria(@ModelAttribute("categoria") Categoria categoria) {
		categoriaRepository.save(categoria);	
		return "redirect:VistaRegistroCategoria";
	}
}
