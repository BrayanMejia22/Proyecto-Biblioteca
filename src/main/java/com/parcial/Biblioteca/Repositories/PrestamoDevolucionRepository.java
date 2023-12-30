package com.parcial.Biblioteca.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.Biblioteca.Documents.PrestamoDevolucion;

public interface PrestamoDevolucionRepository extends MongoRepository<PrestamoDevolucion, String> {

	 
}
