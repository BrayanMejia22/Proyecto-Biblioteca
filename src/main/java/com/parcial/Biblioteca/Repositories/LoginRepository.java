package com.parcial.Biblioteca.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.parcial.Biblioteca.Documents.Login;

public interface LoginRepository extends MongoRepository<Login, String> {

	Login findByUser(String user);

}
