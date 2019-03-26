package com.javaee.acoes.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.acoes.domain.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String>{


	Optional<Cliente> findById(String id);
	
}
