package com.parcial.Biblioteca.Documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "prestamo")
public class Prestamo {

	@Id
	private String id;

	@DocumentReference
	private Libro libro;

	@DocumentReference
	private Estudiante estudiante;

	private Date fechaprestamo;
	private Date fechadevolucion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Date getFechaprestamo() {
		return fechaprestamo;
	}

	public void setFechaprestamo(Date fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}

	public Date getFechadevolucion() {
		return fechadevolucion;
	}

	public void setFechadevolucion(Date fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}	
}
