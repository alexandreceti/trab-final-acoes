package com.javaee.acoes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.domain.Empresa;
import com.javaee.acoes.repositories.AcaoRepository;
import com.javaee.acoes.repositories.ClienteRepository;
import com.javaee.acoes.repositories.EmpresaRepository;

@Service
public class AcaoServiceImpl implements AcaoService{
	
	private AcaoRepository acaoRepository;
	
	private EmpresaRepository empresaRepository;
	
	public AcaoServiceImpl(AcaoRepository acaoRepository, ClienteRepository clienteRepository, EmpresaRepository empresaRepository) {
		this.acaoRepository = acaoRepository;
		this.empresaRepository = empresaRepository;
	}

	@Override
	public Set<Acao> getAll() {
		Set<Acao> acao = new HashSet<>();
		this.acaoRepository.findAll().iterator().forEachRemaining(acao::add);
		return acao;
	}

	@Override
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

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acao createNewAcao(Acao acao) {
		
		Optional<Empresa> empresaOptional = empresaRepository.findById(acao.getEmpresaId());
		
		// localizar empresa
		if (!empresaOptional.isPresent()) {
            throw new IllegalArgumentException("Empresa Not Found");
        } else {
        	System.out.println(empresaOptional.get().toString());
		}

		
		System.out.println("Acao fora do if");
		System.out.println(acao.toString());
		
		if(acaoRepository.findByName(acao.getName()).isEmpty()) {
			System.out.println("Acao fora valida");
			acao.setDtInicial(new Date());
			acao.setDtAtualizacao(new Date());
			return acaoRepository.save(acao);
		
		}else {
			throw new IllegalArgumentException("Acao Already Exists with name: " + acao.getName());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acao saveAcao(String id, Acao acao) {
		acao.setId(id);
		Acao acaoSaved = acaoRepository.save(acao);
		return acaoSaved;
	}
	
	public Acao compraAcao(String id, String clienteId) {
		Acao acaoOptional = getAcaoById(id);
		acaoOptional.setClienteId(clienteId);
		Acao acaoSaved = acaoRepository.save(acaoOptional);
		return acaoSaved;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAcaoById(String id) {
		acaoRepository.deleteById(id);
	}
}