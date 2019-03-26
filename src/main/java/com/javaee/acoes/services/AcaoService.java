package com.javaee.acoes.services;

import java.util.Set;

import com.javaee.acoes.domain.Acao;

public interface AcaoService {

	Set<Acao> getAll();

	Acao getAcaoById(String id);

	Acao createNewAcao(Acao acao);

	Acao saveAcao(String id, Acao acao);
	
	Acao compraAcao(String id, String acao);

	void deleteAcaoById(String id);
}
