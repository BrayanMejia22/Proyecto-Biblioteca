package com.parcial.Biblioteca.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.Biblioteca.Documents.Prestamo;

public interface PrestamoRepository extends MongoRepository<Prestamo, String>{

}
