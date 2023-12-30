package com.parcial.Biblioteca.ControllersVista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcial.Biblioteca.Documents.Login;
import com.parcial.Biblioteca.Repositories.LoginRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginVista {

	@Autowired
	private LoginRepository loginRepository;

	@GetMapping("LoginBiblioteca")
	public String LoginBiblioteca() {
		return "Login";
	}

	@GetMapping("registro")
	public String registro() {
		return "Registro";
	}

	@PostMapping("paginaPrincipal")
	public String paginaPrincipal(@RequestParam String user, @RequestParam String password, @RequestParam String rol,
			Model model, HttpSession session) {

		Login loginBD = loginRepository.findByUser(user);

		if (loginBD != null && loginBD.getPassword().equals(password) && rol.equals(loginBD.getRol())) {

			if ("administrador".equals(rol)) {
				return "redirect:/administrador/Principal";

			} else if ("estudiante".equals(rol)) {
				
				String nombreEstudiante = loginBD.getUser();				
				model.addAttribute("nombreEstudiante", nombreEstudiante);
				return "redirect:/alumno/PrincipalAlumno";
			}
		}
		return "/Login";
	}

	@PostMapping("credenciales")
	public String credenciales(@ModelAttribute("registrarse") Login login) {
		loginRepository.save(login);
		return "Login";
	}

	@GetMapping("CerrarSesion")
	public String CerrarSesion() {
		return "redirect:/LoginBiblioteca";
	}
}
