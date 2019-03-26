package com.javaee.acoes.repositories;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.acoes.domain.Empresa;


@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{

	Optional<Empresa> findById(String id);
}
