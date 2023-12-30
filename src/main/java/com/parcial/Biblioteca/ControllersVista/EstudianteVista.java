package com.parcial.Biblioteca.ControllersVista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.Biblioteca.Documents.Estudiante;
import com.parcial.Biblioteca.Repositories.EstudianteRepository;

@Controller
@RequestMapping("/estudiante")
public class EstudianteVista {
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@GetMapping("/PrincipalEstudiante")
	public String PrincipalEstudiante(Model model) {		
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		model.addAttribute("estudiantes", estudiantes);
		return "Estudiante/estudiante";
	}
	
	@GetMapping("/RegistroEstudiante")
	public String RegistroEstudiante(Model model) {				
		return "Estudiante/RegistroEstudiante";
	}
	
	@PostMapping("/registroEstudiante")
	public String registroEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {		
		estudianteRepository.save(estudiante);
		return "redirect:/estudiante/PrincipalEstudiante";
	}

}
