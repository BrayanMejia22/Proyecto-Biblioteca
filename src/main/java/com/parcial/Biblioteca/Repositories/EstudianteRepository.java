package com.parcial.Biblioteca.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.Biblioteca.Documents.Estudiante;

public interface EstudianteRepository extends MongoRepository<Estudiante, String>{

}
