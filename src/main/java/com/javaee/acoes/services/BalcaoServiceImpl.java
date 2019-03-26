package com.javaee.acoes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.repositories.AcaoRepository;

@Service
public class BalcaoServiceImpl implements BalcaoService {

	private AcaoRepository acaoRepository;
	

	
	public BalcaoServiceImpl(AcaoRepository acaoRepository) {
		this.acaoRepository = acaoRepository;
		
	}
	
	
	public Acao getAcaoById(String id) {
		return getById(id);
	}
	
	private Acao getById(String id) {
		Optional<Acao> acaoOptional = acaoRepository.findById(id);

        if (!acaoOptional.isPresent()) {
            throw new IllegalArgumentException("Acao Not Found For ID value: " + id.toString() );
        }
        return acaoOptional.get();
	}
	
	// Lista de acoes disponiveis
	@Override
	public Set<Acao> getAll() {
		Set<Acao> acao = new HashSet<>();
		this.acaoRepository.findByClienteId("").iterator().forEachRemaining(acao::add);
		return acao;
	}
	
//	@Override
//	public Acao compra(String id, String clienteId) {
//		return getById(id);
//	}
//	
//	@Override
//	public Acao venda(String id, String clienteId) {
//		return getById(id);
//	}
}
