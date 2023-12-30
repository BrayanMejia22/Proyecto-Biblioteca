package com.parcial.Biblioteca.ControllersVista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcial.Biblioteca.Documents.Estudiante;
import com.parcial.Biblioteca.Documents.Libro;
import com.parcial.Biblioteca.Documents.Prestamo;
import com.parcial.Biblioteca.Documents.PrestamoDevolucion;
import com.parcial.Biblioteca.Repositories.EstudianteRepository;
import com.parcial.Biblioteca.Repositories.LibroRepository;
import com.parcial.Biblioteca.Repositories.PrestamoDevolucionRepository;
import com.parcial.Biblioteca.Repositories.PrestamoRepository;

@Controller
@RequestMapping("prestamo")
public class PrestamoVista {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Autowired
	private PrestamoRepository prestamoRepository;

	@Autowired
	private PrestamoDevolucionRepository prestamoDevolucionRepository;

	@GetMapping("/Prestamos")
	public String prestamos(Model model) {
		List<Libro> libros = obtenerLibros();
		List<Estudiante> estudiantes = obtenerEstudiantes();

		model.addAttribute("libros", libros);
		model.addAttribute("estudiantes", estudiantes);

		return "Prestamo/Prestamos";
	}

	private List<Libro> obtenerLibros() {
		return libroRepository.findAll();
	}

	private List<Estudiante> obtenerEstudiantes() {
		return estudianteRepository.findAll();
	}

	@PostMapping("/registroPrestamo")
	public String registroPrestamo(@RequestParam("libro") String libroId,
			@RequestParam("estudiante") String estudianteId,
			@RequestParam("fechaprestamo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaPrestamo,
			@RequestParam("fechadevolucion") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDevolucion) {

		Libro libro = libroRepository.findById(libroId).orElse(null);
		libro.setPrestado(true);
		libroRepository.save(libro);

		Estudiante estudiante = estudianteRepository.findById(estudianteId).orElse(null);

		if (libro != null && estudiante != null) {
			Prestamo prestamo = new Prestamo();
			prestamo.setLibro(libro);
			prestamo.setEstudiante(estudiante);
			prestamo.setFechaprestamo(fechaPrestamo);
			prestamo.setFechadevolucion(fechaDevolucion);
			prestamoRepository.save(prestamo);
		} else {
			System.out.print("error");
		}
		return "redirect:/prestamo/Prestamos";
	}

	@GetMapping("/VerPrestamo")
	public String verPrestamo(Model model) {
		List<Prestamo> prestamos = prestamoRepository.findAll();

		List<String> nombresLibros = new ArrayList<>();
		List<String> nombresEstudiantes = new ArrayList<>();

		for (Prestamo prestamo : prestamos) {
			String nombreLibro = obtenerNombreLibro(prestamo);
			String nombreEstudiante = obtenerNombreEstudiante(prestamo);

			nombresLibros.add(nombreLibro);
			nombresEstudiantes.add(nombreEstudiante);
		}

		model.addAttribute("prestamos", prestamos);
		model.addAttribute("nombresLibros", nombresLibros);
		model.addAttribute("nombresEstudiantes", nombresEstudiantes);

		return "Prestamo/VerPrestamo";
	}

	private String obtenerNombreLibro(Prestamo prestamo) {
		return libroRepository.findById(prestamo.getLibro().getId()).map(Libro::getTitulo).orElse("Desconocido");
	}

	private String obtenerNombreEstudiante(Prestamo prestamo) {
		return estudianteRepository.findById(prestamo.getEstudiante().getId())
				.map(e -> e.getNombre() + " " + e.getApellido()).orElse("Desconocido");
	}

	@GetMapping("/EliminarPrestamo/{id}")
	public String EliminarPrestamo(@PathVariable("id") String id) {
		Prestamo prestamo = prestamoRepository.findById(id).orElse(null);

		if (prestamo != null) {
			Libro libro = prestamo.getLibro();
			libro.setPrestado(false);
			libroRepository.save(libro);

			PrestamoDevolucion prestamoDevolucion = new PrestamoDevolucion();
			prestamoDevolucion.setDevolucion(true);
			prestamoDevolucionRepository.save(prestamoDevolucion);

			prestamoRepository.deleteById(id);
		}
		return "redirect:/prestamo/VerPrestamo";
	}
}
