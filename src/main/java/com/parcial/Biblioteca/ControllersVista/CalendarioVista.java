package com.parcial.Biblioteca.ControllersVista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.Biblioteca.Documents.Prestamo;
import com.parcial.Biblioteca.Repositories.PrestamoRepository;

@Controller
@RequestMapping("/calendario")
public class CalendarioVista {

	@Autowired
	private PrestamoRepository prestamoRepository;

	@GetMapping("/Vistacalendario")
	public String Vistacalendario(Model model) {
		return "Calendario/Calendario";
	}

	@GetMapping("/api/prestamos")
	public ResponseEntity<List<Map<String, Object>>> obtenerPrestamos() {
		List<Prestamo> prestamos = prestamoRepository.findAll();

		List<Map<String, Object>> eventos = new ArrayList<>();

		for (Prestamo prestamo : prestamos) {
			Map<String, Object> evento = new HashMap<>();
			evento.put("title", prestamo.getLibro().getTitulo() + " - " + prestamo.getEstudiante().getNombre() + " " + prestamo.getEstudiante().getApellido());
			evento.put("start", prestamo.getFechaprestamo());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(prestamo.getFechadevolucion());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			evento.put("end", calendar.getTime());
			eventos.add(evento);
		}
		return ResponseEntity.ok(eventos);
	}
}
