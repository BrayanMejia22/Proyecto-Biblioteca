package com.parcial.Biblioteca.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.Biblioteca.Documents.Libro;

public interface LibroRepository extends MongoRepository<Libro, String>{

	Libro findByTitulo(String titulo);

}
