package com.javaee.acoes.bootstrap;

import java.util.Date;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.javaee.acoes.domain.Acao;
import com.javaee.acoes.domain.Cliente;
import com.javaee.acoes.domain.Empresa;
import com.javaee.acoes.repositories.AcaoRepository;
import com.javaee.acoes.repositories.ClienteRepository;
import com.javaee.acoes.repositories.EmpresaRepository;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ClienteRepository clienteRepository;
	
	private EmpresaRepository empresaRepository;
	
	private AcaoRepository acaoRepository;
	
	public ApplicationBootstrap(
			ClienteRepository clienteRepository,
			EmpresaRepository empresaRepository,
			AcaoRepository acaoRepository) {

		this.clienteRepository = clienteRepository;
		this.empresaRepository = empresaRepository;
		this.acaoRepository = acaoRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		if (clienteRepository.count() == 0L) {
			clienteRepository.deleteAll();
			loadCliente();
		}
		if (empresaRepository.count() == 0L) {
			empresaRepository.deleteAll();
			loadEmpresa();
		}
	}
	
	
	
	private void loadCliente() {
		Cliente cl1 = new Cliente();
		cl1.setName("Alexandre Oliveira");
		cl1.setEmail("alexandre@example.com");
		clienteRepository.save(cl1);

		Cliente cl2 = new Cliente();
		cl2.setName("lucas");
		cl2.setEmail("lucas@example.com");
		clienteRepository.save(cl2);

	}
	
	private void loadEmpresa() {
		Empresa emp1 = new Empresa();
		emp1.setName("OLX");
		emp1.setEmail("olx@example.com");
		Empresa acaoEmp1 = empresaRepository.save(emp1);
		
		Acao acao1 = new Acao();
		acao1.setEmpresaId(acaoEmp1.getId());
		acao1.setClienteId("");
		acao1.setName("Acao olx");
		acao1.setValorInicial((float) 10.50);
		acao1.setValor((float) 10.50);
		acao1.setDtInicial(new Date());
		acao1.setDtAtualizacao(new Date());
		
		acaoRepository.save(acao1);

		Empresa emp2 = new Empresa();
		emp2.setName("PucMinas");
		emp2.setEmail("pucminas@example.com");
		Empresa acaoEmp2 = empresaRepository.save(emp2);
		
		Acao acao2 = new Acao();
		acao2.setEmpresaId(acaoEmp2.getId());
		acao2.setClienteId("");
		acao2.setName("Acao Pucminas");
		acao2.setValorInicial((float) 10.50);
		acao2.setValor((float) 10.50);
		acao2.setDtInicial(new Date());
		acao2.setDtAtualizacao(new Date());

		acaoRepository.save(acao2);
	}
}
