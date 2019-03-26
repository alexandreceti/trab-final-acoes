package com.javaee.acoes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javaee.acoes.domain.Acao;

@Repository
public interface AcaoRepository extends MongoRepository<Acao, String>{

	List<Acao> findByName(String name);
	List<Acao> findByClienteId(String clienteId);
}
