package com.parcial.Biblioteca.ControllersWeb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.Biblioteca.Documents.Login;
import com.parcial.Biblioteca.Repositories.LoginRepository;

@RestController
@RequestMapping("login")
public class LoginWeb {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@GetMapping("/")
	public List<Login> findAll(){
		return loginRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Login findById(@PathVariable String id) {
		return loginRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/")
	public Login save(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Login login = mapper.convertValue(body, Login.class);
		return loginRepository.save(login);
	}	
}
