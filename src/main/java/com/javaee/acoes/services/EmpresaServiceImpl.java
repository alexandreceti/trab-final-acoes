package com.javaee.acoes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaee.acoes.domain.Empresa;
import com.javaee.acoes.repositories.EmpresaRepository;;


@Service
public class EmpresaServiceImpl implements EmpresaService{

	private EmpresaRepository empresaRepository;

	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}

	@Override
	public Empresa getEmpresaById(String id) {
		return getById(id);
	}
	
	private Empresa getById(String id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        if (!empresaOptional.isPresent()) {
            throw new IllegalArgumentException("Acao Not Found For ID value: " + id.toString() );
        }
        return empresaOptional.get();
	}
}
