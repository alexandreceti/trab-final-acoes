package com.javaee.acoes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaee.acoes.domain.Cliente;
import com.javaee.acoes.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository clienteRepository;

	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	public Cliente getClienteById(String id) {
		return getById(id);
	}
	
	private Cliente getById(String id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (!clienteOptional.isPresent()) {
            throw new IllegalArgumentException("Acao Not Found For ID value: " + id.toString() );
        }
        return clienteOptional.get();
	}

}
